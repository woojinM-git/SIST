FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test
#  이쯤에 jar파일이 (패키지) 생성된다.


FROM eclipse-temurin:17-jdk
WORKDIR /app
ARG JAR_FILE=/app/build/libs/backend-0.0.1-SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} /docker_1002.jar

ENTRYPOINT [ "java", "-jar", "/docker_1002.jar" ]