#pull down docker.io/dockering/oracle-java8 image
FROM docker.io/dockering/oracle-java8
MAINTAINER tivy2009@126.com

RUN mkdir -p /opt/software
COPY ./target/eurekaclient1-0.0.1-SNAPSHOT.jar /opt/software/

WORKDIR /opt/software/

CMD ["java","-jar","eurekaclient1-0.0.1-SNAPSHOT.jar","--spring.profiles.active=docker"]
