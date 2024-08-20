FROM amazoncorretto:17-alpine AS builder
WORKDIR /app

COPY . .

RUN ./gradlew clean && \
    ./gradlew -x test :api:build

FROM amazoncorretto:17-alpine
ARG PORT=8080
ARG JAR_PATH=api/build/libs/api.jar
WORKDIR /app

COPY --from=builder /app/$JAR_PATH ./app.jar

EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]