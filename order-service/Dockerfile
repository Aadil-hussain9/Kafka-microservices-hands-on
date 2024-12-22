# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
# Set the working directory in the container
WORKDIR /app
# Copy the JAR file from the target directory to the container's working directory
COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar
# Expose the port the application will run on
EXPOSE 8080
# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]




