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
  - JWT 유틸 클래스(토큰생성, 클레임 반환)
  ```java
  public class JwtUtil {
  
      private Key key;
  
      /**
       * 사용할 Signature 해싱 알고리즘: HMAC-SHA256
       * @param secret
       */
  
      public JwtUtil(String secret) {
          this.key = Keys.hmacShaKeyFor(secret.getBytes());
      }
  
      /**
       * Claims: 페이로드에 담기는 데이터들
       * Signature: 토큰이 위조되지 않았는지 검사
       *
       * @param userId
       * @param name
       * @return token
       */
  
      public String createToken(long userId, String name) {
          String token = Jwts.builder()
                  .claim("userId", userId)
                  .claim("name", name)
                  .signWith(key, SignatureAlgorithm.HS256)
                  .compact();
          
          return token;
      }
  
      /**
       * 전달받은 토큰에 저장되어 있는 Claims 객체들을 반환
       *
       * @param token
       * @return claims
       */
  
      public Claims getClaims(String token) {
          Claims claims = Jwts.parserBuilder()
                  .setSigningKey(key)
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
  
          return claims;
      }
  }
  ```
  
  - Spring Security 필터링
  ```java
  /**
   * HTTP 요청에 들어 있는 Authorization 헤더를 필터링하여
   * 성공적이면 Authentication 객체를 SecurityContextHolder 객체에 저장
   */
  
  public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
  
      private JwtUtil jwtUtil;
  
      public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
          super(authenticationManager);
          this.jwtUtil = jwtUtil;
      }
  
      /**
       * 생성된 Authentication 객체가 존재하면
       * SecurityContextHolder에 담음
       *
       * @param request
       * @param response
       * @param chain
       * @throws IOException
       * @throws ServletException
       */
  
      @Override
      protected void doFilterInternal(
              HttpServletRequest request,
              HttpServletResponse response,
              FilterChain chain
      ) throws IOException, ServletException {
  
          Authentication authentication = getAuthentication(request);
          if(authentication != null) {
              SecurityContext context = SecurityContextHolder.getContext();
              context.setAuthentication(authentication);
          }
  
          chain.doFilter(request, response);
      }
  
      /**
       * HTTP 요청의 Authorization 헤더를 검사하여, 토큰에 저장된 Claims들을 가져옴
       * UsernamePasswordAuthentication의 principal에 Claims를 전달하여,
       * 이 정보를 Authentication 객체에 담음
       *
       * @param request
       * @return authentication
       */
  
      private Authentication getAuthentication(HttpServletRequest request) {
          String token = request.getHeader("Authorization");
          if(token == null) {
              return null;
          }
  
          Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
          Authentication authentication = new UsernamePasswordAuthenticationToken(claims, null);
          return authentication;
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