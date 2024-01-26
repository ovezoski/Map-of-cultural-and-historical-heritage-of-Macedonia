FROM maven:3.9.6 AS build

WORKDIR /app

COPY src ./src

COPY pom.xml .

RUN mvn clean package

FROM amazoncorretto:17

WORKDIR /app

COPY --from=build /app .

CMD ["java", "-jar", "./target/map-of-cultural-and-historical-heritage-of-Macedonia-0.0.1.jar"]

EXPOSE 8080