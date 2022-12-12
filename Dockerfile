FROM adoptopenjdk/openjdk11:alpine
EXPOSE:81
ADD target/dorotech_teste-1.0-SNAPSHOT.jar dorotech_teste-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dorotech_teste-1.0-SNAPSHOT.jar"]