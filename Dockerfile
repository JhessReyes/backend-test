FROM eclipse-temurin:20 as build

WORKDIR /tmp/build
COPY . .
RUN ./gradlew shadowJar

FROM eclipse-temurin:20
WORKDIR /app

RUN apt-get update && apt-get install -y postgresql-client

COPY --from=build /tmp/build/build/libs/kotlin-htmx-all.jar .
COPY .env.default .

COPY wait-for-db.sh /app/wait-for-db.sh
RUN chmod +x /app/wait-for-db.sh

EXPOSE 8080
ENV TZ="America/Guatemala"
CMD ["/app/wait-for-db.sh", "java", "-jar", "kotlin-htmx-all.jar"]