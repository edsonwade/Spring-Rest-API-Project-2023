#########################################
# Packaged spring boot app using maven
#########################################
#FROM openjdk:16-jdk-alpine as as builder
FROM maven:3.6-openjdk-11 as builder

RUN mkdir -p /app
WORKDIR /app
ADD pom.xml .
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package -DskipTests


FROM openjdk:11-jdk as runner

COPY --from=builder /app/target/*.jar /app.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java","-jar","/app.jar"]