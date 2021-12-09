FROM java:8
MAINTAINER Godfunc <godfunc@outlook.com>
EXPOSE 9080

VOLUME /tmp
ADD manage.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
