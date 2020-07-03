## 스프링 부트로 구현한 식당 예약 웹 애플리케이션

### 기술 선택

- Java
- Spring-Boot
- Spring Data JPA
- Spring-Security
- Gradle Multi Project
- REST API
- Lombok
- Json Web Tokens

### 3-Tier Architecture

- Presentation : Front-end
- Business : Back-end
- Data Source : DataBase
    
### Business : Layered Architecture

- UI Layer : Controller
- Application Layer : Service
- Domain Layer : Model
- Infrastructure Layer
    
### Domain Model(도메인 모델)

- Restaurant(식당)
- Menu Item(메뉴)
- Review(리뷰)
- Region(지역)
- Category(카테고리)
- User(사용자)
    
### 프로젝트 개발 방법

- TDD (Test Driven Development)
  - JUnit
- Gradle Multi Project
  - eatgo-admin-api
  - eatgo-customer-api
  - eatgo-restaurant-api
  - eatgo-login-api
  - eatgo-common
  
### 예외처리 방법
- 최상위 예외 클래스: BusinessException
  - 비즈니스 로직 상에서 발생하는 모든 예외
  - 하위 클래스
    - InvalidValueException: 유효하지 않은 값으로 인한 예외
    - EntityNotFoundException: 해당 리소스가 없을 경우에 대한 예외
    
- 예외 발생시 통일된 형태의 객체로 응답
  - ErrorResponse
  
### Roles & Permissions Authentication
- ```ApplicationUserPermission```
- ```ApplicationUserRole```
- ```antMatchers()```를 통한 각 API 모듈에 특정 Role을 가진 사용자만 접근할 수 있도록 보안
  - Ex) ```.antMatchers("/customer/api/**").hasRole(CUSTOMER.name())```
- ```@PreAuthorizer()```를 통한 특정 Permission을 가진 사용자만 특정 API를 호출할 수 있도록 보안
  - Ex) ```@PreAuthorize("hasAuthority('restaurant:read')")```

### User Authentication
- 토큰 기반 인증
  - **Json Web Tokens**
  - 사용한 JWT 라이브러리
    - [Java JWT](https://github.com/jwtk/jjwt)
    ```
    dependencies {
        implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
        runtime 'io.jsonwebtoken:jjwt-impl:0.11.2',
        runtime 'io.jsonwebtoken:jjwt-jackson:0.11.2'
    }
    ```
- JwtUsernameAndPasswordAuthenticationFilter
  - extends UsernameAndPasswordAuthenticationFilter
  - ```attemptAuthentication()```
    - ```/login``` URL로 username과 password로 요청시 호출
    - 사용자의 username과 password가 유효한지 검사
  - ```successfulAuthentication()```
    - 유효한 사용자일 경우, Response Header에 JWT 토큰 생성후, 클라이언트로 전송
  
    
### Gradle 커맨드 라인 명령어

- 프로젝트 전체 테스트
  - ./gradlew test

- 단일 모듈 테스트
  - ./gradlew :module-name:test

- 단일 테스트(클래스/메소드)
  - ./gradlew :module-name:test --tests *[class-name].[method-name]
  
- 단일 모듈 실행
  - ./gradlew :module-name:bootRun