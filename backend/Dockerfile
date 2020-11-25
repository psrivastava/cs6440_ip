#dockerfile

# backend
FROM maven:3.6.3-jdk-11 as backend
WORKDIR /backend
COPY backend .
#RUN mkdir -p src/main/resources/static
#COPY --from=frontend /frontend/build src/main/resources/static
RUN mvn clean verify

FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine-slim
COPY --from=backend /backend/target/borenomore-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
RUN adduser -D user
USER user
CMD [ "sh", "-c", "java -Dserver.port=$PORT -jar app.jar" ]