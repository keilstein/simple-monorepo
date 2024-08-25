# Simple Monorepo

**Simple Monorepo** is a tutorial that shows how to build a monorepo from scratch. You can also use it as a template to
bootstrap monorepo projects.

> [!IMPORTANT]  
> **Simple Monorepo** is not a production ready system. It is a tutorial to give guidance especially for personal
> projects which are not mission-critical.
>
> Also, architectural discussions on monorepo vs multi-repo is out of the scope of this tutorial. You can perform a
> search with the keywords [monorepo+vs+multi-repo](https://www.google.com/search?q=monorepo+vs+multi-repo) or reach out
> to your peers for team consensuses and company policies.

Clone the repository and use it, or build it yourself from scratch as an exercise. Step-by-step instructions are
in [Do it yourself from scratch!](README.md#do-it-yourself-from-scratch).

## Product

[🔝](README.md#simple-monorepo)
[⬆️](README.md#simple-monorepo)
[⬇️](README.md#tech-stack)

**Simple Monorepo** contains two libraries and three applications. Libraries are **Test Utils** and **Domain**, whereas
applications are **Open Notify Adapter**, **Geolocation Resolver** and **API Gateway**.

1. [Test Utils](README.md#step-4-add-library-test-utils) contains helpers for tests.
2. [Domain](README.md#step-5-add-library-domain) contains object definitions which are common to all applications in
   the project.
3. [Open Notify Adapter](README.md#step-6-add-application-open-notify-adapter) is a Spring Boot application for
   retrieving realtime data on the International Space Station (ISS) using the endpoints provided by
   the [Open Notify API](http://open-notify.org).
4. [Geolocation Resolver](README.md#step-7-add-application-geolocation-resolver) is another Spring Boot application to
   query geolocation by latitude and longitude. For this service, we use reverse geocoding API provided
   by [BigData Cloud](https://www.bigdatacloud.com).
5. [Api Gateway](README.md#step-8-add-service-api-gateway) is the entry point to all services within.

You must have understood where this is going: **Simple Monorepo** is a platform where we have multiple
geolocation-related services and a single entry point to all.

## Tech Stack

[🔝](README.md#simple-monorepo)
[⬆️](README.md#product)
[⬇️](README.md#do-it-yourself-from-scratch)

**Simple Monorepo** is a Spring Boot Java project build by Gradle. The applications are containerized with Docker for
ops. Lombok annotation processor reduces boilerplate code, and reactive programming is preferred when applicable.

* [Java](https://www.java.com)
* [Gradle](https://gradle.org)
* [Docker](https://www.docker.com)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Lombok](https://projectlombok.org)
* [Reactive](https://spring.io/reactive)

## Do it yourself from scratch!

[🔝](README.md#simple-monorepo)
[⬆️](README.md#tech-stack)
[⬇️](README.md#prerequisites)

### Prerequisites

[🔝](README.md#simple-monorepo)
[⬆️](README.md#do-it-yourself-from-scratch)
[⬇️](README.md#step-1-create-the-project-directory)

#### Terminal

All operating systems provide a default terminal emulator. Additionally, the IDEs provide built-in ones. You can use the
default one or select another from this [list](https://en.wikipedia.org/wiki/List_of_terminal_emulators).

#### Java & Gradle

Install latest LTS version for Java. This is currently **21**. Verify the version by running:

```shell
java --version
```

Install latest version for Gradle. This is currently **8.10**. Verify the version by running:

```shell
gradle --version
```

> [!TIP]  
> You can install Java and Gradle in various ways:
> 1. Recommended way to install them is to use [SDKMAN](https://sdkman.io). SDKMAN is a software development kit manager
     that contains multiple SDKs. You can install multiple versions and distributions of each SDK, and switch between
     them. SDKMAN is available in macOS, Linux and Windows.
> 2. It is also possible to install Java and Gradle through IDEs,
     e.g. [SDKs](https://www.jetbrains.com/help/idea/sdk.html#change-project-sdk) in IntelliJ IDEA.
> 3. You can as well prefer the manual way. In that case, download and install an executable from one of the
     distributors, or use a package management tool such as [apt](https://ubuntu.com/server/docs/package-management)
     or [brew](https://brew.sh) to install.

#### Tree (Optional)

[tree](https://en.wikipedia.org/wiki/Tree_(command)) is a program that lists contents of a directory recursively in a
tree-shaped text format. In this tutorial, it is used to illustrate in detail the results of certain Gradle commands.

#### Docker

Download, install and start [Docker Desktop](https://www.docker.com/products/docker-desktop).

> [!NOTE]
> Alternatively, you can install the **Docker Engine** as described
> in [Install Docker Engine](https://docs.docker.com/engine/install).

#### IDE (Optional)

Download and install your favourite IDE. Recommended IDE for this project is **IntelliJ IDEA**.

| IDE                                                                        | Details                                                                                                  |
|:---------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------|
| [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download) | _A free IDE built on open-source code that provides essential features for Java and Kotlin enthusiasts._ |
| [Visual Studio Code](https://code.visualstudio.com)                        | _Free. Built on open source. Runs everywhere._                                                           |
| [Eclipse](https://www.eclipse.org/downloads)                               | _The Leading Open Platform for Professional Developers_                                                  |

> [!NOTE]  
> IDEs are powerful tools for software development. There are certain cases where [Sublime](https://www.sublimetext.com)
> or any other text editor can do perfectly well or even better. However, IDEs come with a larger set of tools for
> debugging, refactoring, AI tools, etc. Mastering an IDE makes you more performant as a software engineer; it boosts
> your impact. In any case, we recommend [Sublime](https://www.sublimetext.com) as complementary tool.

### Step 1. Create the project directory

[🔝](README.md#simple-monorepo)
[⬆️](README.md#prerequisites)
[⬇️](README.md#step-2-create-the-project)

Create an empty directory `simple-monorepo` and move into it.

```shell
mkdir simple-monorepo
cd simple-monorepo
```

### Step 2. Create the project

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-1-create-the-project-directory)
[⬇️](README.md#step-3-add-build-logic)

#### Step 2.1. Initialize the project using Gradle

We'll build a monorepo that has multiple Java applications and libraries. However, we'll do it from scratch. Therefore,
let's start with initializing a basic Gradle project. Initialize the project by giving all required parameters at once
as follows:

```shell
gradle init \
--type basic \
--project-name simple-monorepo \
--dsl groovy \
<<< 'no'
```

> [!TIP]  
> See [Build Init Plugin](https://docs.gradle.org/current/userguide/build_init_plugin.html) for detailed descriptions of
> allowed parameters.

The directory structure created by this command looks like below:

```text
simple-monorepo
├── build.gradle
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle

3 directories, 7 files
```

- `simple-monorepo/` is the root directory.
- `build.gradle` is the default build script.
- `gradle/` contains the Gradle wrapper files.
- `gradlew` and `gradlew.bat` are the Gradle wrapper scripts.
- `settings.gradle` is the Gradle settings file.

> [!TIP]  
> Alternatively, you can initialize the project using the command `gradle init` in an interactive way.
> See [Run the init task](https://docs.gradle.org/current/samples/sample_building_java_applications_multi_project.html).
> In our case, the resulting directory structure will be the same.

#### Step 2.2. Remove build logic from root

In **Simple Monorepo**, we won't need the build script `build.gradle` in root. Let's clean up the code by removing it.

```shell
rm build.gradle
```

#### Step 2.3. Open project with IDE

At this point, you can go ahead and open the project with your favourite IDE.

#### Step 2.4. Update ignored content

Gradle adds the file [.gitignore](.gitignore) by default with
the [init](README.md#step-21-initialize-the-project-using-gradle) command. Update its content to cover intentionally
untracked files in your development environment.

1. [ ] 👉 Add content from [.gitignore](https://github.com/ulasyilmaz/simple-monorepo/blob/main/.gitignore)

#### Step 2.5. Add README

Every subproject in isolation should have a README file with detailed information about it. The file you are reading
right now is the README of **Simple Monorepo**.

```shell
touch README.md
```

1. [ ] 👉 Add content from [README.md](https://github.com/ulasyilmaz/simple-monorepo/blob/main/README.md).

For more information on README, please refer
to [About READMEs](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes)
documentation of GitHub.

#### Step 2.6. Add CONTRIBUTING

Every project should have a CONTRIBUTING file with detailed information on expectations from contributors.

```shell
touch CONTRIBUTING.md
```

1. [ ] 👉 Add content from [CONTRIBUTING.md](https://github.com/ulasyilmaz/simple-monorepo/blob/main/CONTRIBUTING.md).

#### Step 2.7. Push to Git (Optional)

It is time to push the project into a Git repository. Although this is an optional step, it is highly recommended for
various reasons:

1. Ability to track changes
2. Convenient way to save effort
3. Opportunity of rolling back
4. Easy-share, fast feedback
5. Collaboration
6. Programmer humor: _In case of fire: git commit && git push && leave the building_

> [!TIP]  
> You can create the repository first on [GitHub](https://github.com) or [GitLab](https://gitlab.com), clone it to your
> local computer, move your project files there, commit them, and finally push. Also remember that if you are not a fan
> of CLI, you can do all within your IDE using the built-in tools.

### Step 3. Add build logic

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-2-create-the-project)
[⬇️](README.md#step-4-add-library-test-utils)

We are going to use the directory `buildSrc` to share the build logic between subprojects as described
in [Sharing build logic between subprojects Sample](https://docs.gradle.org/current/samples/sample_convention_plugins.html).
Gradle automatically recognizes `buildSrc` and treats likewise.
See [Use buildSrc to abstract imperative logic](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources).

Let's first create the following directory structure and empty files:

```shell
mkdir -p buildSrc/src/main/groovy

touch buildSrc/build.gradle
touch buildSrc/settings.gradle
touch buildSrc/src/main/groovy/buildlogic.java-application-conventions.gradle
touch buildSrc/src/main/groovy/buildlogic.java-common-conventions.gradle
touch buildSrc/src/main/groovy/buildlogic.java-library-conventions.gradle
```

The directory structure created by this command looks like below:

```text
buildSrc
├── build.gradle
├── settings.gradle
└── src
    └── main
        └── groovy
            ├── buildlogic.java-application-conventions.gradle
            ├── buildlogic.java-common-conventions.gradle
            └── buildlogic.java-library-conventions.gradle

4 directories, 5 files
```

- `build.gradle` is the build script.
- `settings.gradle` is the Gradle settings file.
- `buildlogic.java-application-conventions.gradle` is the build logic that applies to all applications in the project.
- `buildlogic.java-common-conventions.gradle` is the build logic that applies to all applications and libraries in the
  project.
- `buildlogic.java-library-conventions.gradle` is the build logic that applies to all libraries in the project.

1. [ ] 👉 Add content from [build.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/buildSrc/build.gradle)
2. [ ] 👉 Add content
   from [settings.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/buildSrc/settings.gradle)
3. [ ] 👉 Add content
   from [buildlogic.java-application-conventions.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/buildSrc/src/main/groovy/buildlogic.java-application-conventions.gradle)
4. [ ] 👉 Add content
   from [buildlogic.java-common-conventions.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/buildSrc/src/main/groovy/buildlogic.java-common-conventions.gradle)
5. [ ] 👉 Add content
   from [buildlogic.java-library-conventions.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/buildSrc/src/main/groovy/buildlogic.java-library-conventions.gradle)

> [!TIP]  
> An alternative way to generate content for `buildSrc` is to generate an **Application and library project** using
> Gradle and copying the content from there.
>
> ```shell
> mkdir temp
> cd temp
>
> gradle init \
> --type java-application \
> --java-version 21 \
> --project-name simple-monorepo \
> --split-project \
> --dsl groovy \
> --test-framework junit-jupiter \
> --package org.keilstein \
> <<< 'no'
>
> cd ..
> cp -Rf temp/buildSrc .
> rm -rf temp
> ```
>
> In this case, please also remember to remove the `constraints` and the version for JUnit Jupiter
> from [buildlogic.java-common-conventions.gradle](buildSrc/src/main/groovy/buildlogic.java-common-conventions.gradle),
> i.e. ~~`implementation 'org.apache.commons:commons-text:1.11.0'`~~ and
`testImplementation 'org.junit.jupiter:junit-jupiter`~~:`5.10.2'`~~ so that you have the targeted content.

### Step 4. Add library: Test Utils

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-3-add-build-logic)
[⬇️](README.md#step-5-add-library-domain)

The directory `lib` will have all individual libraries we'll create within this project. The name `lib` is a Gradle
convention. Gradle-initiated projects put libraries under `lib` by default. In our case, we'll be using `lib` likewise,
although it is not obligatory.

#### Step 4.1. Create the directory

Let's create a library called **Test Utils** where we have utilities to be used in tests.

```shell
mkdir -p lib/utils/test-utils
```

> [!NOTE]  
> Please note that `test-utils` is purposefully put in [lib/utils](lib/utils) and not under [lib](lib). The reason for
> that is to distinguish test utils from core utils. Later, as an exercise, you can create `core-utils` for helpers to
> be used in the main code, and put it under [lib/utils](lib/utils) as well.

#### Step 4.2. Add build script and content

```shell
touch lib/utils/test-utils/build.gradle
```

1. [ ] 👉 Add content
   from [build.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/build.gradle)

#### Step 4.3. Add project files

```shell
mkdir -p lib/utils/test-utils/src/main/java/org/keilstein/commons/testutils/validation
touch lib/utils/test-utils/src/main/java/org/keilstein/commons/testutils/validation/ValidationUtils.java

mkdir -p lib/utils/test-utils/src/test/java/org/keilstein/commons/testutils/validation
touch lib/utils/test-utils/src/test/java/org/keilstein/commons/testutils/validation/ValidationUtilsTest.java
```

1. [ ] 👉 Add content
   from [ValidationUtils.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/src/main/java/org/keilstein/commons/testutils/validation/ValidationUtils.java)
2. [ ] 👉 Add content
   from [ValidationUtilsTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/src/test/java/org/keilstein/commons/testutils/validation/ValidationUtilsTest.java)

#### Step 4.4. Include library in the project

We have added the library module `test-utils`. Now, we must include it in the project.

Add the following line in [settings.gradle](settings.gradle).

```groovy
include('lib:utils:test-utils')
```

#### Step 4.5. Run the tests

```shell
./gradlew clean test
```

Below is the expected output:

```text
➜  simple-monorepo ./gradlew clean test

BUILD SUCCESSFUL in 2s
13 actionable tasks: 12 executed, 1 up-to-date
```

#### Step 4.6. Add versioning

```shell
touch lib/utils/test-utils/gradle.properties
```

1. [ ] 👉 Add content
   from [gradle.properties](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/gradle.properties)

#### Step 4.7. Add CHANGELOG

```shell
touch lib/utils/test-utils/CHANGELOG.md
```

1. [ ] 👉 Add content
   from [CHANGELOG.md](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/CHANGELOG.md)

#### Step 4.8. Add README

```shell
touch lib/utils/test-utils/README.md
```

1. [ ] 👉 Add content
   from [README.md](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lib/utils/test-utils/README.md)

### Step 5. Add library: Domain

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-4-add-library-test-utils)
[⬇️](README.md#step-6-add-application-open-notify-adapter)

Let's create another library **Domain** for our domain objects repeating the steps described
in [Test Utils](README.md#step-4-add-library-test-utils). 👉 You can download the required content
from [lib/domain](https://github.com/ulasyilmaz/simple-monorepo/tree/main/lib/domain).

1. [ ] [Create the directory](README.md#step-41-create-the-directory)
2. [ ] [Add build script and content](README.md#step-42-add-build-script-and-content)
3. [ ] [Add project files](README.md#step-43-add-project-files)
4. [ ] [Include library in the project](README.md#step-44-include-library-in-the-project)
5. [ ] [Run the tests](README.md#step-45-run-the-tests)
6. [ ] [Add versioning](README.md#step-46-add-versioning)
7. [ ] [Add CHANGELOG](README.md#step-47-add-changelog)
8. [ ] [Add README](README.md#step-48-add-readme)

### Step 6. Add application: Open Notify Adapter

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-5-add-library-domain)
[⬇️](README.md#step-7-add-application-geolocation-resolver)

The directory `app` will have all individual applications we'll create within this project. The name `app` is a Gradle
convention. Gradle-initiated projects put the application in `app` by default. In our case, we'll be using `app`
likewise; however, in a slightly different way, i.e. we will put multiple applications in `app`.

Let's create a Spring Boot application called **Open Notify Adapter** that fetches the current position of ISS
from [Open Notify API](http://open-notify.org).

#### Step 6.1. Create the directory

```shell
mkdir -p app/open-notify-adapter
```

#### Step 6.2. Add build script and content

```shell
touch app/open-notify-adapter/build.gradle
```

1. [ ] 👉 Add content
   from [build.gradle](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/build.gradle)

> [!IMPORTANT]  
> You must have noticed that we don't need to include many of the build script components
> in [build.gradle](app/open-notify-adapter/build.gradle) such as Java version, Maven repository for resolving
> dependencies, usage ofJUnit Jupiter for testing, etc. We have already included them via
> in [buildlogic.java-application-conventions](buildSrc/src/main/groovy/buildlogic.java-application-conventions.gradle)
> and transitively
> in [buildlogic.java-common-conventions](buildSrc/src/main/groovy/buildlogic.java-common-conventions.gradle).

#### Step 6.3. Add project files

```shell
mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/OpenNotifyAdapterApplication.java

mkdir -p app/open-notify-adapter/src/main/resources
touch app/open-notify-adapter/src/main/resources/application.yaml
touch app/open-notify-adapter/src/main/resources/application-local.yaml

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/OpenNotifyAdapterApplicationTests.java
```

1. [ ] 👉 Add content
   from [OpenNotifyAdapterApplication.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/OpenNotifyAdapterApplication.java)
2. [ ] 👉 Add content
   from [application.yaml](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/resources/application.yaml)
3. [ ] 👉 Add content
   from [application-local.yaml](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/resources/application-local.yaml)
4. [ ] 👉 Add content
   from [OpenNotifyAdapterApplicationTests.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/OpenNotifyAdapterApplicationTests.java)

> [!NOTE]  
> Spring Boot code generators generate `application.properties` instead of `application.yaml`. These are equivalent.
> Some may argue that [YAML](https://yaml.org) is easier to read.
>
> Also note that [application-local.yaml](app/open-notify-adapter/src/main/resources/application-local.yaml) creates a
> Spring profile `local` that overwrites some of the properties defined
> in [application.yaml](app/open-notify-adapter/src/main/resources/application.yaml) for the local development
> environment.

#### Step 6.4. Include application in the project

We have added the application module `open-notify-adapter`. Now, we must include it in the project.

Add the following line in [settings.gradle](settings.gradle).

```groovy
include('app:open-notify-adapter')
```

#### Step 6.5. Run the tests

```shell
./gradlew clean test
```

Below is the expected output:

```text
➜  simple-monorepo ./gradlew clean test

BUILD SUCCESSFUL in 8s
26 actionable tasks: 18 executed, 8 up-to-date
```

#### Step 6.6. Create a Spring client to fetch ISS location from Open Notify

##### Step 6.6.1. Add project files

```shell
mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/config
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/config/OpenNotifyAdapterApplicationConfig.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client/model
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client/model/IssNowResponse.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client/OpenNotifyClient.java

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/client
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/client/OpenNotifyClientTest.java
```

1. [ ] 👉 Add content
   from [OpenNotifyAdapterApplicationConfig.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/config/OpenNotifyAdapterApplicationConfig.java)
2. [ ] 👉 Add content
   from [IssNowResponse.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client/model/IssNowResponse.java)
3. [ ] 👉 Add content
   from [OpenNotifyClient.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/client/OpenNotifyClient.java)
4. [ ] 👉 Add content
   from [OpenNotifyClientTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/client/OpenNotifyClientTest.java)

##### Step 6.6.2. Run the tests

```shell
./gradlew clean test
```

Below is the expected output:

```text
➜  simple-monorepo ./gradlew clean test

BUILD SUCCESSFUL in 11s
26 actionable tasks: 20 executed, 6 up-to-date
```

#### Step 6.7. Create a Spring service and a mapper with custom application logic

##### Step 6.7.1. Add project files

```shell
mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/domain
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/domain/Precision.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service/model
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service/model/IssLocationServiceResult.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationServiceResultFactory.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service/IssLocationService.java

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationServiceResultFactoryTest.java

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/service
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/service/IssLocationServiceTest.java
```

1. [ ] 👉 Add content
   from [Precision.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/domain/Precision.java)
2. [ ] 👉 Add content
   from [IssLocationServiceResult.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service/model/IssLocationServiceResult.java)
3. [ ] 👉 Add content
   from [IssLocationServiceResultFactory.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationServiceResultFactory.java)
4. [ ] 👉 Add content
   from [IssLocationService.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/service/IssLocationService.java)
5. [ ] 👉 Add content
   from [IssLocationServiceResultFactoryTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationServiceResultFactoryTest.java)
6. [ ] 👉 Add content
   from [IssLocationServiceTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/service/IssLocationServiceTest.java)

##### Step 6.7.2. Run the tests

```shell
./gradlew clean test
```

Below is the expected output:

```text
➜  simple-monorepo ./gradlew clean test

BUILD SUCCESSFUL in 11s
26 actionable tasks: 20 executed, 6 up-to-date
```

#### Step 6.8. Create a controller to retrieve ISS location

##### Step 6.8.1. Add project files

```shell
mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/model
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/model/IssLocationWebResponse.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationWebResponseFactory.java

mkdir -p app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller
touch app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/IssLocationController.java

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationWebResponseFactoryTest.java

mkdir -p app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/controller
touch app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/IssLocationControllerTest.java
```

1. [ ] 👉 Add content
   from [IssLocationWebResponse.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/model/IssLocationWebResponse.java)
2. [ ] 👉 Add content
   from [IssLocationWebResponseFactory.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationWebResponseFactory.java)
3. [ ] 👉 Add content
   from [IssLocationController.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/main/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/IssLocationController.java)
4. [ ] 👉 Add content
   from [IssLocationWebResponseFactoryTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/mapper/IssLocationWebResponseFactoryTest.java)
5. [ ] 👉 Add content
   from [IssLocationControllerTest.java](https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/src/test/java/org/keilstein/simplemonorepo/opennotifyadapter/controller/IssLocationControllerTest.java)

##### Step 6.8.2. Run the tests

```shell
./gradlew clean test
```

Below is the expected output:

```text
➜  simple-monorepo ./gradlew clean test

BUILD SUCCESSFUL in 11s
26 actionable tasks: 20 executed, 6 up-to-date
```

#### Step 6.9. Add README

```shell
touch app/open-notify-adapter/README.md
```

1. [ ] 👉 Add content from https://github.com/ulasyilmaz/simple-monorepo/blob/main/app/open-notify-adapter/README.md

#### Step 6.10. Run and test manually

Run and test manually the application locally as described in [README](app/open-notify-adapter/README.md#manual-testing)
of **Open Notify Adapter**.

### Step 7. Add application: Geolocation Resolver

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-6-add-application-open-notify-adapter)
[⬇️](README.md#step-8-add-service-api-gateway)

Let's create another Spring Boot application called **Geolocation Resolver** to query geolocation by latitude and
longitude repeating the steps described in [Open Notify Adapter](README.md#step-6-add-application-open-notify-adapter).
👉 You can download the required content
from [app/geolocation-resolver](https://github.com/ulasyilmaz/simple-monorepo/tree/main/app/geolocation-resolver).

1. [ ] [Create the directory](README.md#step-61-create-the-directory)
2. [ ] [Add build script and content](README.md#step-62-add-build-script-and-content)
3. [ ] [Add project files](README.md#step-63-add-project-files)
4. [ ] [Include library in the project](README.md#step-64-include-application-in-the-project)
5. [ ] [Run the tests](README.md#step-65-run-the-tests)
6. [ ] [Create a Spring client to fetch address from BigData Cloud](README.md#step-66-create-a-spring-client-to-fetch-iss-location-from-open-notify)
7. [ ] [Create a Spring service and a mapper with custom application logic](README.md#step-67-create-a-spring-service-and-a-mapper-with-custom-application-logic)
8. [ ] [Create a controller to retrieve address by latitude and longitude](README.md#step-68-create-a-controller-to-retrieve-iss-location)
9. [ ] [Add README](README.md#step-69-add-readme)
10. [ ] Run and test manually the application locally as described
    in [README](app/geolocation-resolver/README.md#manual-testing) of **Geolocation Resolver**.

### Step 8. Add service: API Gateway

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-7-add-application-geolocation-resolver)
[⬇️](README.md#step-9-add-ops)

Now that we have two services in offering, let's add an API Gateway to our _platform_ so that we can have a single entry
point to all services within. In our case, it is a relatively low-complexity task. All we need to do is define a few
rewrite rules for the incoming traffic, and that's it.
See [application.yaml](app/api-gateway/src/main/resources/application.yaml) for the rewrite rules we'll have,
and [build.gradle](app/api-gateway/build.gradle) for the dependencies we'll need. 👉 You can download the required
content from [app/geolocation-resolver](https://github.com/ulasyilmaz/simple-monorepo/tree/main/app/api-gateway).

1. [ ] [Create the directory](README.md#step-61-create-the-directory)
2. [ ] [Add build script and content](README.md#step-62-add-build-script-and-content)
3. [ ] [Add project files](README.md#step-63-add-project-files)
4. [ ] [Include library in the project](README.md#step-64-include-application-in-the-project)
5. [ ] [Run the tests](README.md#step-65-run-the-tests)
6. [x] Rewrite rules are defined in [application.yaml](app/api-gateway/src/main/resources/application.yaml)
7. [ ] [Add README](README.md#step-69-add-readme)
8. [ ] Run and test manually the application locally as described
   in [README](app/api-gateway/README.md#manual-testing) of **API Gateway**.

> [!NOTE]  
> Here is a useful 6-min-read about API
> Gateways: [What does an API gateway do?](https://www.redhat.com/en/topics/api/what-does-an-api-gateway-do), and
> here an extensive documentation on how Spring does
> it: [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway).

### Step 9. Add OPS

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-8-add-service-api-gateway)
[⬇️](README.md#post-requisites)

It is good practice to separate ops-related files from project files. Let's pull all ops-related items such as CI/CD
scripts, Docker files, etc. in directory `ops`.

#### Step 9.1. Add Dockerfiles

```shell
mkdir -p ops/docker
touch ops/docker/api-gateway.Dockerfile
touch ops/docker/geolocation-resolver.Dockerfile
touch ops/docker/open-notify-adapter.Dockerfile
```

1. [ ] 👉 Add content
   from [api-gateway.Dockerfile](https://github.com/ulasyilmaz/simple-monorepo/blob/main/ops/docker/api-gateway.Dockerfile)
2. [ ] 👉 Add content
   from [geolocation-resolver.Dockerfile](https://github.com/ulasyilmaz/simple-monorepo/blob/main/ops/docker/geolocation-resolver.Dockerfile)
3. [ ] 👉 Add content
   from [open-notify-adapter.Dockerfile](https://github.com/ulasyilmaz/simple-monorepo/blob/main/ops/docker/open-notify-adapter.Dockerfile)

#### Step 9.2. Add compose.yaml

```shell
touch compose.yaml
touch compose-local.yaml
```

1. [ ] 👉 Add content from [compose.yaml](https://github.com/ulasyilmaz/simple-monorepo/blob/main/compose.yaml)
2. [ ] 👉 Add content
   from [compose-local.yaml](https://github.com/ulasyilmaz/simple-monorepo/blob/main/compose-local.yaml)

#### Step 9.3. Update ignored content for Docker

Let's add a `.dockerignore` file before we move forward.

```shell
touch .dockerignore
```

1. [ ] 👉 Add content from [.dockerignore](https://github.com/ulasyilmaz/simple-monorepo/blob/main/.dockerignore)

#### Step 9.4. Run the applications

You can run the applications either from the `compose-local.yaml` itself via the IDE or using the following command:

```shell
docker compose -f compose-local.yaml up
```

Test the applications manually one-by-one.

1. [API Gateway](app/api-gateway/README.md#manual-testing)
2. [Geolocation Resolver](app/geolocation-resolver/README.md#manual-testing)
3. [Open Notify Adapter](app/open-notify-adapter/README.md#manual-testing)

## Post-requisites

[🔝](README.md#simple-monorepo)
[⬆️](README.md#step-9-add-ops)
[⬇️](README.md#codeowners)

### Lombok Config

[🔝](README.md#simple-monorepo)
[⬆️](README.md#post-requisites)
[⬇️](README.md#codeowners)

Lombok is in our [Tech Stack](README.md#tech-stack). Therefore, it makes sense to add a configuration file for
Lombok. See article [Lombok Configuration System](https://www.baeldung.com/lombok-configuration-system) for more on the
preferred settings in **Simple Monorepo**.

```shell
touch lombok.config
```

1. [ ] 👉 Add content from [lombok.config](https://github.com/ulasyilmaz/simple-monorepo/blob/main/lombok.config).

### CODEOWNERS

[🔝](README.md#simple-monorepo)
[⬆️](README.md#lombok-config)
[⬇️](README.md#license)

_You can use a CODEOWNERS file to define individuals or teams that are responsible for code in a repository._

```shell
touch CODEOWNERS
```

For more information on CODEOWNERS, please refer
to [About code owners](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-code-owners)
documentation of GitHub.

### LICENSE

[🔝](README.md#simple-monorepo)
[⬆️](README.md#codeowners)
[⬇️](README.md#simple-monorepo)

This is an optional step. Especially for open source projects, though, it is strongly recommended.

```shell
touch LICENSE
```

1. [ ] 👉 Add content from [LICENSE](https://github.com/ulasyilmaz/simple-monorepo/blob/main/LICENSE) for **GNU General
   Public License v3.0**.
