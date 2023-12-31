# firestore-quickstart

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
.vnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/firestore-quickstart-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Google Cloud Firestore ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-google-cloud-services/main/firestore.html)): Use Google Cloud Firestore NOSQL database service

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Building docker

Before building the container image run:

```shell script
mvnw package
```

Then, build the image with:

```shell script
docker build --platform=linux/amd64 -f Dockerfile -t quarkus/firestore-quickstart-jvm .
```

Then run the container using:

```shell script
docker run -i --rm -p 8080:8080 quarkus/firestore-quickstart-jvm
```

Use Cloud Build to build your image, it will upload to a Google Cloud Storage bucket all the files of your application (except the ones ignored by the `.gcloudignore`file), build your Docker image and push it to Google Container Registry (GCR).

```shell script
gcloud builds submit --tag gcr.io/employeeapp-2c1fa/firestore-quickstart
```

Finally, use Cloud Run to launch your application.
```shell script
gcloud run deploy --image gcr.io/employeeapp-2c1fa/firestore-quickstart
```
