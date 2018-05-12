#!/bin/bash
#Rotate the Nginx logs to prevent a single logfile from consuming too much disk space. 
LOGS_PATH=/usr/local/nginx/logs
YESTERDAY=$(date -d "yesterday" +%Y-%m-%d)
mv ${LOGS_PATH}/access.log ${LOGS_PATH}/access_${YESTERDAY}.log
mv ${LOGS_PATH}/error.log ${LOGS_PATH}/error_${YESTERDAY}.log
mv ${LOGS_PATH}/eir.access.log ${LOGS_PATH}/eir.access_${YESTERDAY}.log
kill -USR1 $(cat /usr/local/nginx/sbin/nginx.pid)
