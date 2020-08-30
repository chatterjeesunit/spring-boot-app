FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine
LABEL maintainer="Sunit Chatterjee (developerpod.com)"
RUN adduser --no-create-home --disabled-password springuser
USER springuser:springuser
COPY build/libs/spring-boot-app-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]