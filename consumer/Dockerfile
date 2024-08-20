FROM amazoncorretto:17-alpine AS builder
WORKDIR /app

COPY . .

RUN ./gradlew clean && \
    ./gradlew -x test :consumer:build

FROM amazoncorretto:17-alpine
ARG PORT=8081
ARG JAR_PATH=consumer/build/libs/consumer.jar
WORKDIR /app

COPY --from=builder /app/$JAR_PATH ./app.jar

EXPOSE $PORT
ENTRYPOINT ["java", "-jar", "app.jar"]