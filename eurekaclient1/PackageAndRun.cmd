@echo off
@echo ##################################EurekaClient1##################################
@echo add the config "127.0.0.1 discovery1 discovery2" to host file with your compute
@echo #################################################################################
@echo start to package and generat to the jar file.
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true
cd target
@echo start to run the jar file.
title eureka client1
call java -jar eurekaclient1-0.0.1-SNAPSHOT.jar