# Build stage
FROM gradle:8.3.0-jdk17-alpine AS build

WORKDIR /video-hosting-channels
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle build --no-daemon

# Run stage
FROM openjdk:17-alpine

COPY --from=build /video-hosting-channels/build/libs/video-hosting-channels-1.0.0.jar /app/video-hosting-channels-1.0.0.jar

ENTRYPOINT ["java", "-jar", "app/video-hosting-channels-1.0.0.jar"]
