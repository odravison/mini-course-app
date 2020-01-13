FROM maven:3.6.3-jdk-8-slim
WORKDIR /usr/src/java-code
COPY .. /usr/src/java-code/
RUN cd .. && mvn package -DskipTests && cd minicourses-application

WORKDIR /config
COPY ./src/main/resources/application-docker.yml /config/application-docker.yml

WORKDIR /usr/src/java-app
RUN cp /usr/src/java-code/target/*.jar ./app.jar
EXPOSE 9000
ENTRYPOINT [ "sh", "-c", "java -jar ./app.jar --spring.config.location=/config/application-docker.yml" ]
