# BUILD STAGE
# Use the Gradle base image with JDK 21 for building the application
FROM gradle:latest AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the current local directory to /app, which is the working directory in the container
COPY . .

# Clean the existing build and package the application to create a JAR file
RUN ./gradlew clean build

# RUN STAGE
# Use Eclipse Temurin JRE 21 for running the application
FROM eclipse-temurin:21-jre-alpine

# Copy the executable JAR file from the build stage to the /app directory in the container
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Set the command to run your Spring application when the container starts
CMD ["java", "-jar", "/app/app.jar"]
