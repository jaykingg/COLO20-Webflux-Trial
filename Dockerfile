FROM openjdk:17-slim

# 작업 디렉토리 설정
WORKDIR /app

# 의존성 파일들을 컨테이너에 복사
COPY ./gradlew /app/gradlew
COPY ./gradle /app/gradle
COPY ./build.gradle.kts /app/build.gradle.kts
COPY ./settings.gradle.kts /app/settings.gradle.kts

# Gradle Wrapper 실행권한 부여
RUN chmod +x /app/gradlew

# 의존성 설치
RUN ./gradlew build --no-daemon

# 실행 파일 복사
COPY . .

# 애플리케이션 빌드
RUN ./gradlew clean build

# 포트 설정
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "/app/build/libs/*-SNAPSHOT.jar"]
