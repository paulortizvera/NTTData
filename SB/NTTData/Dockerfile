FROM openjdk:8-alpine
MAINTAINER paulortizvera@gmail.com
ADD target/NTTData-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/app.jar"]