FROM gradle:8.10-jdk21 AS build

WORKDIR /simple-monorepo

COPY . .
RUN gradle --no-daemon clean bootJar --exclude-task test

FROM eclipse-temurin:21-jdk

WORKDIR /simple-monorepo
COPY --from=build /simple-monorepo/app/geolocation-resolver/build/libs/geolocation-resolver-0.0.1-SNAPSHOT.jar geolocation-resolver.jar

ENTRYPOINT ["java", "-jar", "/simple-monorepo/geolocation-resolver.jar"]