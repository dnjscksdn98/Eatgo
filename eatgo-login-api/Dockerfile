FROM openjdk:8
ADD build/libs/eatgo-login-api-0.0.1-SNAPSHOT.jar login.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "login.jar"]