FROM openjdk:11
COPY target/amazon-rewies-0.0.1-SNAPSHOT.war amazon-rewies.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "amazon-rewies.war"]
