FROM openjdk:17-alpine

# JAR_FILE 변수를 선언하면서 = 뒤의 경로를 넣는다
ARG JAR_FILE=/build/libs/ex0929_docker-0.0.1-SNAPSHOT.jar

# 위에서 경로를 담은 JAR_FILE 을 /docker_boot.jar 로 사용하겠다는 의미
COPY ${JAR_FILE} /docker_boot.jar

# ENTRYPOINT는 도커 이미지가 실행될 때 사용되는 기본 커맨드를 지정 (강제)
# 띄어쓰기를 인식하지 못하므로 띄어쓰기는 문자열을 끊어서 입력해줘야함
ENTRYPOINT [ "java", "-jar", "/docker_boot.jar" ]