#!/bin/bash

host="192.168.1.14"
port=50091
destimage=eurekaimage/eurekaclient1

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
docker run -d -p $port:$port -e host=$host -e port=$port $destimage
