#!/bin/bash
source ./_common.sh

echo "脚本用于提供EurekaServer服务注册部署的独立的主机，但是微服务部署在Docker的场景下的启动脚本，需要配合Dockerfile文件使用."

getip
host=$?
port=50092
destimage=eurekaimage/eurekaclient1
randomport=true

if [ ! -n "$host" ]
then 
	echo "the computer ipAddress is blank,exit program."
	exit 1
fi
echo "the computer ipAddress is $host"

if [ randomport ]
then
	getprot
	port=$?
fi
echo "the current port is :$port"

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

echo "staring docker run ."
#docker run -d -p $port:$port -e host=$host -e port=$port $destimage
docker run -d -p $port:9091 -e host=$host -e port=$port $destimage
