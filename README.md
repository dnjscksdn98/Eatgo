## 스프링 부트로 구현한 레스토랑 예약 앱

### 3-Tier Architecture

- Presentation : Front-end
- Business : Back-end
- Data Source : DataBase
    
### Business : Layered Architecture

- UI Layer : Controller
- Application Layer : Service
- Domain Layer : Model
- Infrastructure Layer
    
### Domain Model

- Restaurant
- Menu Item
- Review
- Region
- Category
- User
    
### 기술 선택

- Java
- Spring-Boot
- Spring Data JPA
- Spring-Security
- Gradle Project
- REST API
- Lombok
    
### 프로젝트 개발 방법

- TDD (Test Driven Development)
  - JUnit
- Gradle Multi Project
  - eatgo-admin-api
  - eatgo-customer-api
  - eatgo-common

### Gradle Multi Project

- 고객 API
- 어드민 API

- gradlew.bat build
- gradlew.bat assemble
- gradlew.bat test
- gradlew.bat check

- gradle run/debug config -> tasks: clean test
    
### Gradle 커맨드 라인 명령어

- 프로젝트 전체 테스트
  - ./gradlew test

- 단일 모듈 테스트
  - ./gradlew :module-name:test

- 단일 테스트(클래스/메소드)
  - ./gradlew :module-name:test --tests [class-name].[method-name]
  
- 단일 모듈 실행
  - ./gradlew :module-name:bootRun