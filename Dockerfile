FROM openjdk:8 AS BUILD_IMAGE
ENV APP_HOME=/root/dev/myapp/
WORKDIR $APP_HOME
COPY /build.gradle gradlew gradlew.bat $APP_HOME
COPY /gradle $APP_HOME/gradle
# download dependencies
COPY . .
RUN ./gradlew bootRun --stacktrace --debug --info
