FROM openjdk:8-jdk-alpine 
WORKDIR /workspace/app

COPY ./mvnw .
COPY ./.mvn .mvn
COPY ./pom.xml .
COPY ./api-services api-services
COPY ./db db
COPY ./services services
COPY ./web web
COPY ./migration migration
COPY ./security security
COPY ./checkconffold checkconffold

RUN ./mvnw install -DskipTests

ENTRYPOINT ["java","-jar","web/target/web-1.0-exe.war"]
