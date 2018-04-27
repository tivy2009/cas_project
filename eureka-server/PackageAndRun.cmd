@echo off
@echo ###############################EurekaServerÎ±¼¯Èº²¿Êð###############################
@echo add the config "127.0.0.1 discovery1 discovery2" to host file with your compute
@echo #################################################################################
@echo start to package and generat to the jar file.
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true
cd target
@echo start to run the jar file with profils discovery1 and discovery2.
start cmd /c "title discovery2 && java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=discovery2"
title discovery1
call java -jar eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=discovery1