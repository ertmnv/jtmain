FROM openjdk:8-jdk-alpine as build
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
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]
