FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/spring-boot-ecommerce.jar"]

#ENTRYPOINT ["java","-cp","app:app/lib/*","spring-boot-ecommerce"]
##9a5c0c1b3bce4383a4af6c8ada492490