FROM openjdk:8
ADD build/libs/eatgo-admin-api-0.0.1-SNAPSHOT.jar admin.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "admin.jar"]