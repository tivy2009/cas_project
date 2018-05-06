#!/bin/bash

host="192.168.1.13"
port=50092
destimage=eurekaimage/eurekaclient1
randomport=true

#获取主机IP地址，排除Docker虚拟网卡地址
for e in `ifconfig|grep "flags="|grep -v docker0:|grep -v lo:|awk '{print $1}'|tr -d ":"`
do
	ipaddress=`ifconfig ${e}|grep inet|grep -v 127.0.0.1|grep -v inet6|grep -v '^$|#'|awk '{print $2}'|tr -d "addr:"`
	if [ -n "$ipaddress" ] 
	then 
 		echo "the computer ipAddress is $ipaddress"
 		host="$ipaddress"
	fi
done

if [ ! -n "$host" ]
then 
	echo "the computer ipAddress is blank,exit program."
	exit 1
fi

if [ randomport ]
then
	existFlag=0
	count=0
	while [ $existFlag == 0 ]
	do
		count=$(($count+1))
		#查询当前已经正在使用的40000以后的端口号（包含40000）
		existPorts=`netstat -ntulp|grep :|awk '{print $4}'|awk -F ':' '{if($NF>=40000){print $NF}}'`
		#echo "系统当前已经使用的非安全端口号:$existPorts"
	
		num=$(cat /dev/urandom | head -n 10 | cksum | awk -F ' ' '{print $1}') 
		urandomNum=$(($num%25535+40000))
		echo "随机获取一个没有使用的非安全端口号:$urandomNum"
	
		if [ -n "$existPorts" ]
		then
			for v in "$existPorts"
			do
				if [ ${v} == "$urandomNum" ]
				then
					existFlag=1
				fi
			done
		fi
		if [ $existFlag == 0 ]
		then
			port=$urandomNum	
			break
		fi
		if [ $count > 500 ]
		then
			echo "随机获取一个非安全端口号失败，系统退出."
			exit 1
			break
		fi	
	done
fi
echo "当前使用的端口为:$port"

echo "脚本用于提供EurekaServer服务注册部署的独立的主机，但是微服务部署在Docker的场景下的启动脚本，需要配合Dockerfile文件使用."
echo "staring build project."
#mvn clean package spring-boot:repackage -Dmaven.test.skip=true

echo "stop all the running docker ."
docker stop $(sudo docker ps -q)

echo "rm all the docker ."
docker rm $(sudo docker ps -a -q)

echo "rm the docker image."
docker rmi $destimage

echo "staring docker build ."
docker build -t $destimage .

# 2bce248f52d0 : 镜像ID或名称
# 192.168.1.14 : 属主主机的IP地址

echo "staring docker run ."
#docker run -d -p $port:$port -e host=$host -e port=$port $destimage
docker run -d -p $port:9091 -e host=$host -e port=$port $destimage
