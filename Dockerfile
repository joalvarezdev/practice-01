FROM openjdk:11-jdk

WORKDIR /app

COPY target/challenge.jar .

EXPOSE 8090

ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "challenge.jar"]

