#! /bin/bash  
# Filename:_commmon.sh

#Get the host IP address and exclude the Docker virtual network address.
function getip()  
{  
	for e in `ifconfig|grep "flags="|grep -v docker0:|grep -v lo:|awk '{print $1}'|tr -d ":"`
	do
		ipaddress=`ifconfig ${e}|grep inet|grep -v 127.0.0.1|grep -v inet6|grep -v '^$|#'|awk '{print $2}'|tr -d "addr:"`
		if [ -n "$ipaddress" ] 
		then 
	 		return "$ipaddress";
		fi
	done  
}

#Get a random host free port
function getprot()
{
	existFlag=0
	count=0
	while [ $existFlag == 0 ]
	do
		count=$(($count+1))
		#Query the port number that is currently in use and the port number Greater than or equal to 40000
		existPorts=`netstat -ntulp|grep :|awk '{print $4}'|awk -F ':' '{if($NF>=40000){print $NF}}'`
		#echo "The non secure port number that is currently used by the system :$existPorts"
		
		num=$(cat /dev/urandom | head -n 10 | cksum | awk -F ' ' '{print $1}') 
		urandomNum=$(($num%25535+40000))
		echo "random get a non secure port number:$urandomNum"
		
		if [ -n "$existPorts" ]
		then
			for v in "$existPorts"
			do
				if [ ${v} == "$urandomNum" ]
				then
					existFlag=1
					break
				fi
			done
		fi
		if [ $existFlag == 0 ]
		then
			return $urandomNum;	
			break
		fi
		if [ $count > 500 ]
		then
			echo "random get a non secure port number failed. System quit."
			exit 1
			break
		fi	
	done
}