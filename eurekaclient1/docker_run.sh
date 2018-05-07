#!/bin/bash
. ./_common.sh

echo -e "The script is used to provide a stand-alone host for the EurekaServer service registration deployment,\nbut the micro service is deployed in the Docker scenario and needs to be used with the Dockerfile file."

#the host name
host="192.168.1.14"
isautosethost=true

#the host mapping port, ant to be called by the user
israndomoutport=true
outport=50092

#the port of the application in the docker
dockerport=9091

#the name of the docker image
customimage=eurekaimage/eurekaclient1
echo "the custom image is $customimage"

echo "staring build project."
#mvn clean package spring-boot:repackage -Dmaven.test.skip=true

echo "stop all the running docker ."
docker stop $(sudo docker ps -q)

echo "rm all the docker ."
docker rm $(sudo docker ps -a -q)

echo "rm the docker image."
docker rmi $customimage

echo "staring docker build ."
docker build -t $customimage .

# auto setting external host ipaddress
if [ isautosethost ]; then
    getIpAddress
    host=$ipAddress
    if [ ! -n "$host" ]; then 
        echo "the computer ipAddress is blank,exit program."
        exit 1
    fi
fi
echo "the computer ipAddress is $host"

# auto setting external host port number
if [ israndomoutport ]
then
    getRandomProt
    if [ $randomProt > 0 ]; then
        outport=$randomProt
    fi
fi
echo "the current port is :$outport"

echo "staring docker run ."
docker run -d -p $outport:$dockerport -e host=$host -e port=$outport $customimage