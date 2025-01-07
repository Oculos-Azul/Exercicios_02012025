FROM maven:3.9.8-eclipse-temurin-22

WORKDIR /app

COPY pom.xml /app
RUN mvn dependency:resolve

COPY src /app/src

CMD ["mvn", "test"]
