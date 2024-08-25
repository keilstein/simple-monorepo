# Geolocation Resolver

**Geolocation Resolver** is a Spring Boot application to query geolocation by latitude and longitude. For this
service, we use reverse geocoding API package provided by [BigData Cloud](https://www.bigdatacloud.com).

## Development

### Prerequisites

**Geolocation Resolver** is a subproject of a monorepo project. Therefore, all prerequisites needed for **Simple
Monorepo** is needed here as well. See Section [Prerequisites](../../README.md#prerequisites) for details.

### Local deployment

You can deploy **Geolocation Resolver** to local through IntelliJ IDEA, Gradle or Docker. All starts the application on
port 8082 of localhost, i.e. http://localhost:8082.

#### IntelliJ IDE

Easiest way is to use IntelliJ IDEA, i.e. by
running [GeolocationResolverApplication](src/main/java/org/keilstein/simplemonorepo/geolocationresolver/GeolocationResolverApplication.java)
in the IDE.

#### Gradle

You can run `bootRun` command of Gradle the project root.

```shell
../../gradlew bootRun --args='--spring.profiles.active=local'
```

#### Docker

You can deploy to local by running the service `geolocation-resolver` in [compose-local.yaml](../../compose-local.yaml).

```shell
docker compose -f ../../compose-local.yaml up
```

### Manual Testing

Once deployed to localhost, you can perform a request to http://localhost:8082/v1/address and see the result. Try it
with [Postman](https://www.postman.com), [curl](https://curl.se) and your favorite browser.

```shell
curl \
--verbose \
--request GET 'http://localhost:8082/v1/address'
```

> [!TIP]  
> Using the  [HTTP Client plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html) built in
> IntelliJ IDEA Ultimate, you can perform HTTP requests directly in the IntelliJ IDE. At times, it can be a very useful
> tool. Please find an example in [requests](../../requests/geolocation-resolver).