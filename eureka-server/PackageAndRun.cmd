@echo off
@echo ######################EurekaServerα��Ⱥ����######################
@echo ����host�ļ������  127.0.0.1 discovery1 discovery2
@echo ###############################################################
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true
cd target
start cmd /k "title discovery1 && java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=discovery1"
title "discovery2"
call java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=discovery2
cmd /k
cd..