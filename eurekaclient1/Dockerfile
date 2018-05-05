#pull down centos image
FROM centos
MAINTAINER tivy2009@126.com

RUN mkdir -p /opt/software
ADD ./jdk-8u171-linux-x64.tar.gz /opt/software/
COPY ./eurekaclient1-0.0.1-SNAPSHOT.jar /opt/software/

#set environment variable
ENV JAVA_HOME /opt/software/jdk1.8.0_171
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib

#容器运行时监听的端口
EXPOSE  9091

WORKDIR /opt/software/

#define entry point which will be run first when the container starts up
#ENTRYPOINT java -jar /opt/software/eurekaclient1-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","eurekaclient1-0.0.1-SNAPSHOT.jar"]
