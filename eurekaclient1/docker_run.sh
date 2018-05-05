#!/bin/bash

var host="192.168.1.14"
var port=50091

echo "脚本用于提供EurekaServer服务注册部署的独立的主机，但是微服务部署在Docker的场景下的启动脚本，需要配合Dockerfile文件使用."
echo "staring build project."
mvn clean package spring-boot:repackage -Dmaven.test.skip=true

echo "staring docker build ."
docker build -t eurekaimage/eurekaclient1 .

# 2bce248f52d0 : 镜像ID或名称
# 192.168.1.14 : 属主主机的IP地址

echo "staring docker run ."
docker run -d -p $port:$port -e host='$host' -e port=$port eurekaimage/eurekaclient1