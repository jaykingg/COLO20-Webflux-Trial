# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-slim

WORKDIR /app

COPY . .

RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.7-bin.zip && \
    unzip gradle-8.7-bin.zip && \
    mv gradle-8.7 /opt/ && \
    rm gradle-8.7-bin.zip

ENV PATH=$PATH:/opt/gradle-8.7/bin

RUN gradle build --no-daemon

EXPOSE 8080

CMD ["gradle", "bootRun"]
