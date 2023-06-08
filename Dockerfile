FROM adoptopenjdk:11-jre-hotspot as build
COPY . /app
WORKDIR /app
RUN chmod +x mvnw \
    && ./mvnw --version \
    && ./mvnw clean package

FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY --from=build /app/target/${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]