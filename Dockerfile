FROM openjdk:8-jre
ADD target/gcp-java-1.0.jar app.jar
ADD src/resources/webapp/index.html src/resources/webapp/index.html
CMD ["java", "-jar", "app.jar"]