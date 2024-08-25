FROM gradle:8.10-jdk21 AS build

WORKDIR /simple-monorepo

COPY . .
RUN gradle --no-daemon clean bootJar --exclude-task test

FROM eclipse-temurin:21-jdk

WORKDIR /simple-monorepo
COPY --from=build /simple-monorepo/app/open-notify-adapter/build/libs/open-notify-adapter-0.0.1-SNAPSHOT.jar open-notify-adapter.jar

ENTRYPOINT ["java", "-jar", "/simple-monorepo/open-notify-adapter.jar"]