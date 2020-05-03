# <레스토랑 예약 앱>

## 3-Tier Architecture

    - Presentation : Front-end
    - Business : Back-end
    - Data Source : DataBase
    
## Business : Layered Architecture

    - UI Layer : Controller
    - Application Layer : Service
    - Domain Layer : Model
    - Infrastructure Layer
    
## Domain Layer

    - Restaurant
    - Menu Item
    - Review
    
## 기술 선택

    - Java
    - Spring-Boot
    - Gradle
    - REST API
    - Spring Data JPA
    - Lombok
    
## 프로젝트 개발 방법

    - TDD (Test Driven Development)
    - Gradle Multi Project

## Gradle Multi Project

    - 고객 API
    - 어드민 API

    - gradlew.bat build
    - gradlew.bat assemble
    - gradlew.bat test
    - gradlew.bat check
    
    - gradle run/debug config -> tasks: clean test