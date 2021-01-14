FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#ENTRYPOINT ["java","-cp","app:app/lib/*","spring-boot-ecommerce"]