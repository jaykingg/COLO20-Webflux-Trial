# Colo 2.0 project preview

#### Spec, Convention fix 에 앞 서 지속적인 논의가 필요합니다.

***

## Colo 2.0 sample project

### 🔆 Goal

Colo 2.0 project 를 위한 Start version 을 Set up 한다.  
최대한 확장 가능성을 열어두고 개발한다.   
최소한의 Convention 을 적립하며 개발한다.   
나올 수 있는 개발 case 를 Sample project 에 녹인다.
***

### ✍🏻 Sketch

Micro services & 1:1 mapping database
<그림>
***

### 🟠 Server Data Exchange

#### Api call

- 특정 동기처리가 포함된 경우 비동기의 의미가 퇴색됨

#### Messaging Queue

- ✅ kafka
    - Pub/Sub 형태
    - 실시간/대용량 처리(Clustering, Parallel)
    - 영속성 보장(추적 가능)
- Rabbit MQ
    - Message Broker 형태
    - 보안/신뢰성있는 처리
    - 관리적 측면에 유리

***

### 🟠 Project Tech Spec

#### ✅ Language

- Kotlin(Java17, Coroutine)

#### ✅ Framework

- SpringBoot, Webflux

#### ✅ Database

- R2DBC (Mysql)

#### ✅ Authorize

- Jwt

#### ✅ Api docs

- Swagger

#### ✅ Test

- Kotest(Behavior Spec, BDD)

#### ✅ Deploy

- Docker image

***

### 🟠 Expect

- MSA 안에서 Event 기반의 비동기 서비스를 개발하여 대용량 처리.
- TestCode 와 Code Convention 을 적립하여 적은 비용으로, 지속가능한 개발.

*** 

### 🟠 Discussion

- About Framework

|         MVC + JPA          |                       Webflux  + R2DBC                        |
|:--------------------------:|:-------------------------------------------------------------:|
|             동기             |                       비동기   <br/>    \                        |
|           ORM ✅            | ORM❌<br/>관계형 Mapping 지원❌<br/>JPA(Persist, Loading)❌ <br/>DDL❌ |
|         요청당 Thread         |             Coroutine 을 사용하여 비선점 Multi-tasking 가능             |
| 순서가 보장되며 정형적인 Project 에 적합 |                     Event 기반 Project 에 적합                     |
|           정보 많음            |                 러닝 커브 존재, 정보 부족, 성장해가고있는 생태계                  |
|                            |        MySQL 의 경우 공식 Connector 지원❌<br/>외부 라이브러리로 연결 가능        
|                            |            Messaging Queue 와 조합으로 MSA, 대용량 처리에 적합             |

> ❔숫자의 증감이 많고 중요한 도메인인데, 동기보다 비동기 일까?  
> ❔결국 B2B 인데, 그 정도의 대용량일까? (TPS 1000건 이상)?

> ### Issue
>> MongoDB(or H2) 의 경우 Reactive/JPA/Aggregation/Coroutine 을 자체 지원한다.  
> > 기존의 RDB(Mysql, Postgres) 는 R2DBC 를 사용하여 Reactive 를 지원한다.   
> > R2DBC + JPA + Coroutine 조합으로 Sample Project 에 적용 시도.
>
> > Coroutine 의 Bean 을 찾지 못하여 Compile Error 발생
>
> ### Reason
> > 결과적으로 단순 Bean 등록이 안된 것이 아니라, JPA 와 R2DBC 의 Dependency 간 충돌로 Context 에 등록이 안됨.  
> > 덧붙여서, DbClient 를 Custom 하면 JPA 를 사용할 수 있으나 Mybatis 같이 수동으로 Query 를 모두 작성해야함. (지저분해짐.)
>
> ### How to Solve
> > 1. JPA Dependency 삭제
> > 2. R2DBC 규격에 맞게 Domain(Entity) field mapping 처리
> > 3. JPA 함수들 Coroutine 함수로 모두 변경(Controller/Service/Repository)

- Database
    - RDB, Aurora(Mysql 호환)
- Project Structure
    - "Domain" 이 중심이 되게끔 구조 설정

~~~bash
├── build.gradle.kts
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── src
    ├── main
    │   ├── kotlin
    │   │   └── org
    │   │       └── example
    │   │           └── sample
    │   │               ├── WebfluxStarterApplication.kt
    │   │               ├── config
    │   │               │   └── SwaggerConfig.kt
    │   │               ├── controller
    │   │               │   └── GuideController.kt
    │   │               ├── core
    │   │               │   ├── ApiResponse.kt
    │   │               │   ├── ErrorResponse.kt
    │   │               │   ├── Exceptions.kt
    │   │               │   └── ExceptionsHandler.kt
    │   │               ├── domain
    │   │               │   ├── Guide.kt
    │   │               │   └── GuideType.kt
    │   │               ├── payload
    │   │               │   ├── CreateGuidePayload.kt
    │   │               │   └── UpdateGuidePayload.kt
    │   │               ├── repository
    │   │               │   └── GuideRepository.kt
    │   │               └── service
    │   │                   └── GuideService.kt
    │   └── resources
    │       ├── application-local.yaml
    │       ├── application.yaml
    │       └── schema
    │           └── Guide-Create.sql
    └── test
        ├── kotlin
        │   └── org
        │       └── example
        │           └── sample
        │               ├── config
        │               │   ├── Faker.kt
        │               │   └── KotlinFixture.kt
        │               ├── controller
        │               └── integration
        │                   ├── GetAllGuideIT.kt
        │                   └── IntegrationTestSample.kt
        └── resources
            └── application-test.yaml
~~~

- Api Docs
    - Swagger 적용, 페이지에서 Document, Api test 모두 가능
    - 기존 Postman 를 사용하여 Documentation 대체 (계정이슈 등)
- Infra & Cloud
    - 담당자가 따로 있을 것인지? in,out bound set up 이나 container 관리 등
    - ECS -> Fargate 방식으

***

### 🟠 TODO And Done

1. CRUD (@Query 포함) ✅
2. Custom Response(Response, Error) ✅
2. Swagger ✅
3. Test code convention✅
4. Test code migration(basic -> coroutine))✅
4. Docker️️▶️
5. Jwt
6. Project Guide Md 작성
6. Caching
