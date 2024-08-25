FROM gradle:8.10-jdk21 AS build

WORKDIR /simple-monorepo

COPY . .
RUN gradle --no-daemon clean bootJar --exclude-task test

FROM eclipse-temurin:21-jdk

WORKDIR /simple-monorepo
COPY --from=build /simple-monorepo/app/api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

ENTRYPOINT ["java", "-jar", "/simple-monorepo/api-gateway.jar"]