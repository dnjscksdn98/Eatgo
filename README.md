## 스프링 부트로 구현한 식당 예약 웹 애플리케이션

### Build Jar
```
./gradlew :eatgo-customer-api:clean :eatgo-customer-api:bootJar
./gradlew :eatgo-restaurant-api:clean :eatgo-restaurant-api:bootJar
./gradlew :eatgo-admin-api:clean :eatgo-admin-api:bootJar
./gradlew :eatgo-login-api:clean :eatgo-login-api:bootJar
```

### Create Docker Image
```
docker build --build-arg ENVIRONMENT=prod -t eatgo-customer-api:1.0.0 .
docker build --build-arg ENVIRONMENT=prod -t eatgo-restaurant-api:1.0.0 .
docker build --build-arg ENVIRONMENT=prod -t eatgo-admin-api:1.0.0 .
docker build --build-arg ENVIRONMENT=prod -t eatgo-login-api:1.0.0 .
```

### Pull MySQL Image
```
docker pull mysql:5.7
```

### Run with Docker
```
docker-compose up
```

### API Documentation
- [Eatgo-Admin-API](https://documenter.getpostman.com/view/11430148/T17NZjUc)
- [Eatgo-Customer-API](https://documenter.getpostman.com/view/11430148/T17Na4Rb)
- [Eatgo-Owner-API](https://documenter.getpostman.com/view/11430148/T17Na4Rd)
- [Eatgo-Login-API](https://documenter.getpostman.com/view/11430148/T17Na4Vu)

### 기술 선택

- Java 11
- Spring-Boot
- Spring Data JPA
- Spring-Security
- Gradle Multi Project
- REST API
- Lombok
- Json Web Tokens
- Docker
    
### Business : Layered Architecture

- UI Layer : Controller
- Application Layer : Service
- Domain Layer : Model
- Infrastructure Layer
    
### Domain Model(도메인 모델)

- Restaurant(식당)
- Reservation(예약)
- Menu Item(메뉴)
- Review(리뷰)
- Region(지역)
- Category(카테고리)
- User(사용자)
    
### 프로젝트 개발 방법

- TDD (Test Driven Development)
  - JUnit5
- Gradle Multi Project
  - eatgo-admin-api
  - eatgo-customer-api
  - eatgo-restaurant-api
  - eatgo-login-api
  - eatgo-common
  
### 예외처리 방법

<img width="479" alt="img01" src="https://user-images.githubusercontent.com/59077132/87236927-48f66800-c42a-11ea-85bc-75e2ce9e8719.PNG">

- 최상위 예외 클래스: ```BusinessException```
  - 비즈니스 로직 상에서 발생하는 모든 예외
  - 하위 클래스
    - ```InvalidValueException```
      - 서버로 데이터를 전달하여 요청시, 해당 데이터가 이미 존재하는 값일 경우 예외 발생
      - 하위 클래스 예) ```xxxDulicationException```
    - ```EntityNotFoundException```
      - 데이터베이스에 해당 리소스가 없을 경우 예외 발생
      - 하위 클래스 예) ```xxxNotFoundException```
    
- 발생 가능한 예외들은 개별적으로 관리하기 위하여 ```Enum``` 형태로 분리: ```ErrorCode```
  - 예)
  ```java
  INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
  ENTITY_NOT_FOUND(404, "C002", "Entity not found"),

  // User
  INVALID_LOGIN_VALUE(400, "U001", "Login input value is invalid"),
  EMAIL_DUPLICATION(400, "U002", "This email already exists"),
  PASSWORD_MISMATCH(400, "U003", "Password must match with confirm password"),
  ```
  
- 예외 발생시 응답 구조 설명: ```ErrorReponse```
  - 예)
  ```json
  {
    "transaction_time": "2020-07-10T12:58:23.744377",
    "status": 400,
    "result_code": "C001",
    "message": "Invalid input value",
    "errors": [
        {
            "field": "name",
            "value": "",
            "reason": "반드시 값이 존재하고 길이 혹은 크기가 0보다 커야 합니다."
        }
    ]
  }
  ```
  
  - ```transaction_time```: 요청 시간
  - ```status```: 상태 코드
  - ```result_code```: 에러 코드
  - ```message```: 에러 메시지
  - ```errors```:
    - 요청시 전송된 데이터 중에서 유효하지 않은 데이터들의 상세 정보
    - ```field```: 해당 데이터명
    - ```value```: 전송한 데이터
    - ```reason```: 유효하지 않은 이유
    
### 성공시 응답 구조 설명: ```SuccessReponse```
- 예)
```json
{
    "transaction_time": "2020-07-11T12:22:23.744331",
    "status": 200,
    "result_code": "OK",
    "message": "OK",
    "data": [
        {
            "id": 1,
            "email": "tester@example.com",
            "name": "tester",
            "level": 100,
            "role": "ADMIN",
            "created_at": "2020-07-10 13:14:25",
            "created_by": "ADMIN"
        }
    ]
}
```
- ```transaction_time```: 요청 시간
- ```status```: 상태 코드
- ```result_code```: 성공 코드
- ```message```: 성공 메시지
- ```data```: 
  
### Roles & Permissions Authentication
**```ApplicationUserPermission```**
- 사용자의 permission 정보를 가지고 있는 ```Enum```
- 예)
```java
USER_READ("user:read"),
USER_WRITE("user:write"),

RESTAURANT_READ("restaurant:read"),
RESTAURANT_WRITE("restaurant:write"),
```

**```ApplicationUserRole```**
- 사용자의 role과 해당 role이 가지고 있는 구체적인 permission을 가지는 ```Enum```
- 예)
```java
CUSTOMER(Sets.newHashSet(
        RESTAURANT_READ,
        REVIEW_READ,
        REVIEW_WRITE,
        MENUITEM_READ,
        CATEGORY_READ,
        REGION_READ,
        RESERVATION_WRITE
)),
```

**```antMatchers()```**
- 각 API 모듈에 특정 role을 가진 사용자만 접근할 수 있도록 경로에 권한 부여
- 예)
```java
.antMatchers("/management/api/v1/**").hasRole(ADMIN.name())
```

**```@PreAuthorize()```**
- 각 API 함수에 특정 permission을 가진 사용자만 접근할 수 있도록 권한 부여
- 예)
```java
@GetMapping
@PreAuthorize("hasAuthority('user:read')")
public SuccessResponse<List<UserResponseDto>> list() {
    return userService.list();
}
```

### User Authentication
- 토큰 기반 인증
  - **Json Web Tokens**
  - 사용한 JWT 라이브러리
    - https://github.com/jwtk/jjwt
    ```
    dependencies {
        implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
        runtime 'io.jsonwebtoken:jjwt-impl:0.11.2',
        runtime 'io.jsonwebtoken:jjwt-jackson:0.11.2'
    }
    ```
**```JwtUsernameAndPasswordAuthenticationFilter```**
- extends ```UsernameAndPasswordAuthenticationFilter```
- ```attemptAuthentication()```
  - 디폴트로 ```/login``` URL로 username과 password로 요청시 호출
  - 사용자의 username과 password가 유효한지 검사
- ```successfulAuthentication()```
  - 만약 유효한 사용자일 경우, JWT 토큰 생성후 Response Header에 추가하여 클라이언트로 전달
  
**```JwtTokenVerifier```**
- extends ```OncePerRequestFilter```
- ```doFilterInternal()```
  - 매번 클라이언트로 요청시 Request Header에 담긴 JWT Token이 유효한지 검사
    
**```ApplicationSecurityConfig```**

- ```configure()```
  - Spring-Security 환경 설정
  ```java
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, jwtSecretKey))
            .addFilterAfter(new JwtTokenVerifier(jwtConfig, jwtSecretKey, applicationUserService), JwtUsernameAndPasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/management/api/v1/**").hasRole(ADMIN.name())
            .anyRequest()
            .authenticated();
  }
  ```
