# Spring Cloud Microservices - Implementation Summary

## Project Overview

This is a complete **Spring Cloud Microservices Architecture** learning project demonstrating:

- **Service Discovery**: Eureka service registry
- **API Gateway**: Spring Cloud Gateway with request routing
- **Microservices**: Independent services (Account, Loan)
- **Global Logging**: Centralized request logging through API Gateway

---

## Complete Project Structure

```
microservices/
├── README.md                          # Complete documentation
├── test-microservices.sh              # Testing script
├── eureka-discovery-server/           # Service Registry
│   ├── pom.xml
│   ├── src/main/java/com/cognizant/
│   │   └── EurekaDiscoveryServerApplication.java
│   └── src/main/resources/
│       └── application.properties
├── account/                           # Account Microservice
│   ├── pom.xml
│   ├── src/main/java/com/cognizant/
│   │   ├── AccountApplication.java
│   │   ├── models/
│   │   │   └── Account.java
│   │   └── controllers/
│   │       └── AccountController.java
│   └── src/main/resources/
│       └── application.properties
├── loan/                              # Loan Microservice
│   ├── pom.xml
│   ├── src/main/java/com/cognizant/
│   │   ├── LoanApplication.java
│   │   ├── models/
│   │   │   └── Loan.java
│   │   └── controllers/
│   │       └── LoanController.java
│   └── src/main/resources/
│       └── application.properties
└── api-gateway/                       # API Gateway
    ├── pom.xml
    ├── src/main/java/com/cognizant/
    │   ├── ApiGatewayApplication.java
    │   └── filters/
    │       └── LogFilter.java
    └── src/main/resources/
        └── application.properties
```

---

## Key Implementation Files

### 1. Eureka Discovery Server

**EurekaDiscoveryServerApplication.java**
```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaDiscoveryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
    }
}
```

**application.properties**
```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=OFF
```

**pom.xml** (Key dependencies)
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

---

### 2. Account Service

**AccountApplication.java**
```java
@SpringBootApplication
@EnableEurekaClient
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
```

**Account.java** (Domain Model)
```java
public class Account {
    private String number;
    private String type;
    private long balance;
    // Getters and setters
}
```

**AccountController.java**
```java
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @GetMapping("/{number}")
    public Account getAccount(@PathVariable String number) {
        return new Account(number, "savings", 234343);
    }
}
```

**application.properties**
```properties
spring.application.name=account-service
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 3. Loan Service

**LoanApplication.java**
```java
@SpringBootApplication
@EnableEurekaClient
public class LoanApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanApplication.class, args);
    }
}
```

**Loan.java** (Domain Model)
```java
public class Loan {
    private String number;
    private String type;
    private long loanAmount;
    private long emi;
    private int tenure;
    // Getters and setters
}
```

**LoanController.java**
```java
@RestController
@RequestMapping("/loans")
public class LoanController {
    @GetMapping("/{number}")
    public Loan getLoan(@PathVariable String number) {
        return new Loan(number, "car", 400000, 3258, 18);
    }
}
```

**application.properties**
```properties
spring.application.name=loan-service
server.port=8081
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

### 4. API Gateway

**ApiGatewayApplication.java**
```java
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

**LogFilter.java** (Global Request Logging)
```java
@Component
public class LogFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("Request Method: " + request.getMethod());
        logger.info("Request Path: " + request.getPath());
        logger.info("Request Headers: " + request.getHeaders());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
```

**application.properties**
```properties
spring.application.name=api-gateway
server.port=9090
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

---

## Dependencies

All projects use **Spring Boot 2.7.13** with **Spring Cloud 2021.0.3**

### Eureka Server Dependencies
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

### Microservices Dependencies (Account, Loan)
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

### API Gateway Dependencies
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

---

## Service Communication Flow

```
┌─────────────────────────────────────────────────────────────┐
│ 1. Client requests /account-service/accounts/ACC123         │
│    (Note: lowercase service-id from Eureka)                 │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
        ┌──────────────────────────────────────────────┐
        │ API Gateway (9090)                           │
        │ - Receives request                           │
        │ - LogFilter logs: method, path, headers      │
        │ - Looks up "account-service" in Eureka       │
        └──────────────────────┬───────────────────────┘
                               │
                               ▼
        ┌──────────────────────────────────────────────┐
        │ Eureka Registry                              │
        │ - Returns: 127.0.0.1:8080                    │
        └──────────────────────┬───────────────────────┘
                               │
                               ▼
        ┌──────────────────────────────────────────────┐
        │ Account Service (8080)                       │
        │ - Receives request at /accounts/ACC123       │
        │ - Returns account JSON                       │
        └──────────────────────┬───────────────────────┘
                               │
                               ▼
        ┌──────────────────────────────────────────────┐
        │ API Gateway (9090)                           │
        │ - Receives response                          │
        │ - Routes response back to client             │
        └──────────────────────────────────────────────┘
```

---

## Service Registration Process

When each microservice starts:

1. **Application Startup**: Spring Boot initializes the application
2. **Eureka Client Activation**: `@EnableEurekaClient` activates
3. **Service Registration**: Sends heartbeat to Eureka Server with:
   - Service Name (e.g., `account-service`)
   - Instance IP and Port
   - Health Check Endpoint
4. **Eureka Registry Update**: Service appears in registry
5. **API Gateway Discovery**: Gateway retrieves updated service list
6. **Ready for Requests**: All services synchronized

---

## Build and Deployment

### Build all services
```bash
# Eureka Server
cd eureka-discovery-server && mvn clean compile

# Account Service
cd account && mvn clean compile

# Loan Service
cd loan && mvn clean compile

# API Gateway
cd api-gateway && mvn clean compile
```

### Run all services (in separate terminals, in order)
```bash
# Terminal 1: Eureka (MUST START FIRST)
cd eureka-discovery-server && mvn spring-boot:run

# Terminal 2: Account Service
cd account && mvn spring-boot:run

# Terminal 3: Loan Service
cd loan && mvn spring-boot:run

# Terminal 4: API Gateway
cd api-gateway && mvn spring-boot:run
```

### Verify
```bash
# Check Eureka Registry
curl http://localhost:8761/eureka/apps

# Test Account directly
curl http://localhost:8080/accounts/ACC123

# Test Loan directly
curl http://localhost:8081/loans/L456

# Test through Gateway
curl http://localhost:9090/account-service/accounts/ACC123
curl http://localhost:9090/loan-service/loans/L456
```

---

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Framework | Spring Boot | 2.7.13 |
| Spring Cloud | Spring Cloud | 2021.0.3 |
| Service Discovery | Netflix Eureka | 1.10.17 |
| API Gateway | Spring Cloud Gateway | 3.1.3 |
| Load Balancing | Spring Cloud LoadBalancer | 3.1.3 |
| Build Tool | Maven | 3.x |
| Java | Java | 1.8 |
| Web | Spring Web (Tomcat) | 5.3.28 |
| Reactive | Reactor/Netty | For Gateway |

---

## Key Learnings

✅ **Service Discovery Pattern**: Microservices register themselves with Eureka for dynamic discovery  
✅ **API Gateway Pattern**: Single entry point for all client requests  
✅ **Decoupling**: Services independent and independently deployable  
✅ **Resilience**: Services can restart without manual routing updates  
✅ **Observability**: Global filters enable centralized logging  
✅ **Load Balancing**: Gateway automatically load-balances requests to multiple instances  

---

## Next Steps

1. **Scale Services**: Run multiple instances on different ports, register all with Eureka
2. **Fault Tolerance**: Add Hystrix circuit breaker for resilience
3. **Inter-Service Communication**: Use Feign or RestTemplate for service-to-service calls
4. **Authentication**: Implement JWT at gateway level
5. **Monitoring**: Add Spring Boot Actuator and Prometheus metrics
6. **Configuration**: Use Spring Cloud Config for centralized configuration
