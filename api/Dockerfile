FROM java:8
MAINTAINER Godfunc <godfunc@outlook.com>
EXPOSE 9090

VOLUME /tmp
ADD api.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
