@echo off
@echo ##################################spring-security-oauth##################################
@echo add the config "127.0.0.1 discovery1 discovery2" to host file with your compute
@echo #################################################################################
@echo start to package and generat to the jar file.
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true
cd target
@echo start to run the jar file.
title spring-security-oauth
call java -jar spring-security-oauth-0.0.1-SNAPSHOT.jar
cmd /k