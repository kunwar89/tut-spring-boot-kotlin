FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/myapp/
WORKDIR $APP_HOME
COPY build.gradle.kts gradlew gradlew.bat $APP_HOME
COPY /gradle $APP_HOME/gradle
# download dependencies
COPY . .
RUN ./gradlew build --stacktrace --debug --info
CMD ["java","-jar","/root/dev/myapp/target/blog-0.0.1-SNAPSHOT.jar"]
