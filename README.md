## 스프링 부트로 구현한 식당 예약 웹 애플리케이션

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
- Gradle Multi Project
- REST API
- Lombok
- Json Web Tokens
    
### 프로젝트 개발 방법

- TDD (Test Driven Development)
  - JUnit
- Gradle Multi Project
  - eatgo-admin-api
  - eatgo-customer-api
  - eatgo-common
  
### 사용자 인증 방법(User Authentication)
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
  - JWT 생성 클래스
  ```java
  public class JwtUtil {
  
      private Key key;
  
      public JwtUtil(String secret) {
          // 사용할 Signature 해싱 알고리즘: HMAC-SHA256
          this.key = Keys.hmacShaKeyFor(secret.getBytes());
      }
  
      public String createToken(long userId, String name) {
          String token = Jwts.builder()
                  .claim("userId", userId)  // Claims: 페이로드에 담기는 데이터들
                  .claim("name", name)
                  .signWith(key, SignatureAlgorithm.HS256)  // Signature: 토큰이 위조되지 않았는지 검사
                  .compact();
          
          return token;
      }
  }
  ```
    
### Gradle 커맨드 라인 명령어

- 프로젝트 전체 테스트
  - ./gradlew test

- 단일 모듈 테스트
  - ./gradlew :module-name:test

- 단일 테스트(클래스/메소드)
  - ./gradlew :module-name:test --tests *[class-name].[method-name]
  
- 단일 모듈 실행
  - ./gradlew :module-name:bootRun