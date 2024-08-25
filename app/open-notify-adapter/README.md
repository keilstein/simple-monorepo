# Open Notify Adapter

**Open Notify Adapter** is a Spring Boot application for retrieving realtime data on International Space Station (ISS)
using the endpoints provided by the [Open Notify API](http://open-notify.org).

## Development

### Prerequisites

**Open Notify Adapter** is a subproject of a monorepo project. Therefore, all prerequisites needed for **Simple Monorepo
** is needed here as well. See Section [Prerequisites](../../README.md#prerequisites) for details.

### Local deployment

You can deploy **Open Notify Adapter** to local through IntelliJ IDEA, Gradle or Docker. All starts the application on
port 8083 of localhost, i.e. http://localhost:8083.

#### IntelliJ IDE

Easiest way is to use IntelliJ IDEA, i.e. by
running [OpenNotifyAdapterApplication](src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/OpenNotifyAdapterApplication.java)
in the IDE.

#### Gradle

You can run `bootRun` command of Gradle the project root.

```shell
../../gradlew bootRun --args='--spring.profiles.active=local'
```

#### Docker

You can deploy to local by running the service `open-notify-adapter` in [compose-local.yaml](../../compose-local.yaml).

```shell
docker compose -f ../../compose-local.yaml up
```

### Manual Testing

Once deployed to localhost, you can perform a request to http://localhost:8083/v1/iss-location and see the result. Try
it with [Postman](https://www.postman.com), [curl](https://curl.se) and your favorite browser.

```shell
curl \
--verbose \
--request GET 'http://localhost:8083/v1/iss-location'
```

> [!TIP]  
> Using the  [HTTP Client plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html) built in
> IntelliJ IDEA Ultimate, you can perform HTTP requests directly in the IntelliJ IDE. At times, it can be a very useful
> tool. Please find an example in [requests](../../requests/open-notify-adapter).