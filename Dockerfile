FROM openjdk:11

COPY target/grid-simulator.jar /artifact/grid-simulator.jar

EXPOSE 8080/tcp

# Run it
ENTRYPOINT ["java", "-jar", "/artifact/grid-simulator.jar"]