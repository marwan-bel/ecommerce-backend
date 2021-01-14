FROM maven:3-alpine

COPY pom.xml ecommerce-backend/

COPY src/ ecommerce-backend/src/

WORKDIR ecommerce-backend/

RUN mvn clean install

EXPOSE 8086

ENTRYPOINT [ "java", "-jar", "/pipeline/target/spring-boot-ecommerce.jar"]

#ENTRYPOINT ["java","-cp","app:app/lib/*","spring-boot-ecommerce"]
##9a5c0c1b3bce4383a4af6c8ada492490