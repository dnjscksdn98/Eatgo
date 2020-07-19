FROM openjdk:8
ADD build/libs/eatgo-customer-api-0.0.1-SNAPSHOT.jar customer.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "customer.jar"]