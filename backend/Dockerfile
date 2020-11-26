#dockerfile

# backend
FROM maven:3.6.3-jdk-11 as backend
RUN mkdir -p /backend
WORKDIR /backend
COPY . .
#RUN mkdir -p src/main/resources/static
#COPY --from=frontend /frontend/build src/main/resources/static
RUN mvn clean verify

FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine-slim

# https://gist.github.com/wwerner/05a8e627e8f3ba18300db745511d3bcb
# see https://devcenter.heroku.com/articles/exec#enabling-docker-support
#RUN apk add --no-cache curl bash openssh python
#ADD heroku-exec.sh /app/.profile.d/heroku-exec.sh
#RUN chmod a+x /app/.profile.d/heroku-exec.sh
# ====================================================================


COPY --from=backend /backend/target/drugabuse-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
RUN adduser -D user
USER user
# https://stackoverflow.com/questions/44491257/how-to-reduce-spring-boot-memory-usage
CMD [ "sh", "-c", "java -XX:+UseSerialGC -Xss512k -XX:MaxRAM=500m -Dserver.port=$PORT -jar app.jar" ]
