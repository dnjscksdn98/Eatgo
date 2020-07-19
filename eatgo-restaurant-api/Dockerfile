FROM openjdk:8
ADD build/libs/eatgo-restaurant-api-0.0.1-SNAPSHOT.jar restaurant.jar
ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java", "-jar", "restaurant.jar"]