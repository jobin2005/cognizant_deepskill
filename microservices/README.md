# Microservices Architecture with Spring Cloud

This project demonstrates a complete microservices architecture with Spring Cloud, including service discovery and API Gateway.

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────────┐
│                        Client Application                        │
└────────────────────────────┬────────────────────────────────────┘
                             │
                             ▼
        ┌────────────────────────────────────────┐
        │  API Gateway (Port: 9090)              │
        │  - Spring Cloud Gateway                │
        │  - Global Request Logging Filter       │
        │  - Service Discovery Integration       │
        └────────────────────────────────────────┘
                             │
        ┌────────────────────┴────────────────────┐
        │                                         │
        ▼                                         ▼
┌──────────────────────────┐        ┌──────────────────────────┐
│  Account Service         │        │  Loan Service            │
│  (Port: 8080)            │        │  (Port: 8081)            │
│  - Account Controller    │        │  - Loan Controller       │
│  - Eureka Discovery      │        │  - Eureka Discovery      │
│  - GET /accounts/{number}│        │  - GET /loans/{number}   │
└──────────────────────────┘        └──────────────────────────┘
        │                                         │
        └────────────────────┬────────────────────┘
                             │
                             ▼
        ┌────────────────────────────────────────┐
        │  Eureka Discovery Server (Port: 8761)  │
        │  - Service Registry                    │
        │  - Service Instance Health             │
        └────────────────────────────────────────┘
```

## Services

### 1. Eureka Discovery Server
**Port:** 8761  
**Purpose:** Central service registry for service discovery

**Startup:**
```bash
cd eureka-discovery-server
mvn spring-boot:run
```

**Verify:** Open http://localhost:8761 in browser
- Initially shows empty instances (no applications registered yet)

---

### 2. Account Service
**Port:** 8080  
**Service ID:** account-service

**Features:**
- REST endpoint: `GET /accounts/{number}`
- Returns account details for a given account number
- Eureka Discovery Client (self-registers with Eureka)

**Startup:**
```bash
cd account
mvn spring-boot:run
```

**Test Endpoint (Direct):**
```bash
curl http://localhost:8080/accounts/ACC00987987973432
```

**Response:**
```json
{
  "number": "ACC00987987973432",
  "type": "savings",
  "balance": 234343
}
```

---

### 3. Loan Service
**Port:** 8081  
**Service ID:** loan-service

**Features:**
- REST endpoint: `GET /loans/{number}`
- Returns loan details for a given loan number
- Eureka Discovery Client (self-registers with Eureka)

**Startup:**
```bash
cd loan
mvn spring-boot:run
```

**Test Endpoint (Direct):**
```bash
curl http://localhost:8081/loans/L00987987972342
```

**Response:**
```json
{
  "number": "L00987987972342",
  "type": "car",
  "loanAmount": 400000,
  "emi": 3258,
  "tenure": 18
}
```

---

### 4. API Gateway
**Port:** 9090  
**Purpose:** Unified entry point for all microservices with request logging

**Features:**
- Automatic service discovery from Eureka
- Global request logging (method, path, headers)
- Automatic routing based on service names
- Lowercase service ID conversion

**Startup:**
```bash
cd api-gateway
mvn spring-boot:run
```

---

## Complete Startup Sequence

### Step 1: Start Eureka Discovery Server (Required First)
```bash
cd microservices/eureka-discovery-server
mvn spring-boot:run
```
**Output:** You should see "Tomcat started on port(s): 8761"

**Verify Eureka Dashboard:**
```bash
curl http://localhost:8761/eureka/apps
```

---

### Step 2: Start Account Service (in new terminal)
```bash
cd microservices/account
mvn spring-boot:run
```
**Output:** You should see:
- "Tomcat started on port(s): 8080"
- Registration logs indicating successful registration with Eureka

**Verify Registration in Eureka:**
```bash
curl http://localhost:8761/eureka/apps/account-service
```

---

### Step 3: Start Loan Service (in new terminal)
```bash
cd microservices/loan
mvn spring-boot:run
```
**Output:** You should see:
- "Tomcat started on port(s): 8081"
- Registration logs indicating successful registration with Eureka

**Verify Registration in Eureka:**
```bash
curl http://localhost:8761/eureka/apps/loan-service
```

---

### Step 4: Start API Gateway (in new terminal)
```bash
cd microservices/api-gateway
mvn spring-boot:run
```
**Output:** You should see:
- "Tomcat started on port(s): 9090"
- Logs indicating discovery locator routes configured

---

## Testing Through API Gateway

### Test Account Service Through Gateway
```bash
curl http://localhost:9090/account-service/accounts/ACC00987987973432
```

**Expected Response:**
```json
{
  "number": "ACC00987987973432",
  "type": "savings",
  "balance": 234343
}
```

---

### Test Loan Service Through Gateway
```bash
curl http://localhost:9090/loan-service/loans/L00987987972342
```

**Expected Response:**
```json
{
  "number": "L00987987972342",
  "type": "car",
  "loanAmount": 400000,
  "emi": 3258,
  "tenure": 18
}
```

---

## Verify Service Discovery

### Check Service Registration at Eureka

**View All Registered Services:**
```bash
curl http://localhost:8761/eureka/apps
```

**After all services started, you should see:**
1. ACCOUNT-SERVICE with instance at port 8080
2. LOAN-SERVICE with instance at port 8081
3. API-GATEWAY with instance at port 9090

---

## Global Request Logging

The API Gateway includes a `LogFilter` that logs all incoming requests.

**Log Output Format:**
```
Request Method: GET
Request Path: /account-service/accounts/ACC00987987973432
Request Headers: {...}
```

**View logs in API Gateway console to verify all requests are being logged.**

---

## Configuration Details

### Eureka Discovery Server (`eureka-discovery-server/src/main/resources/application.properties`)
```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=OFF
```

### Account Service (`account/src/main/resources/application.properties`)
```properties
spring.application.name=account-service
server.port=8080
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### Loan Service (`loan/src/main/resources/application.properties`)
```properties
spring.application.name=loan-service
server.port=8081
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### API Gateway (`api-gateway/src/main/resources/application.properties`)
```properties
spring.application.name=api-gateway
server.port=9090
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

---

## Dependencies

### Cross-Project Dependencies (Spring Cloud 2021.0.3)
- **spring-cloud-starter-netflix-eureka-server** (Eureka Server)
- **spring-cloud-starter-netflix-eureka-client** (Account, Loan, Gateway)
- **spring-cloud-starter-gateway** (API Gateway)
- **spring-cloud-starter-loadbalancer** (Load balancing)

---

## Troubleshooting

### Services not registering with Eureka
1. Ensure Eureka server is started first (port 8761)
2. Check that `eureka.client.service-url.defaultZone` points to correct Eureka URL
3. Review logs for "Registering with Eureka"

### API Gateway returning 503
1. Verify all services are registered in Eureka
2. Check service names match in Eureka vs. gateway URL (use lowercase service IDs)
3. Verify ports: Account (8080), Loan (8081)

### Cannot reach services directly
- Account: `http://localhost:8080/accounts/{number}`
- Loan: `http://localhost:8081/loans/{number}`
- Gateway: `http://localhost:9090/{service-name}/{endpoint}`

---

## Project Dependencies Summary

```
Spring Boot 2.7.13
├── Spring Framework 5.3.28
├── Spring Cloud 2021.0.3
│   ├── Netflix Eureka (Server + Client)
│   ├── Spring Cloud Gateway
│   └── Spring Cloud LoadBalancer
└── DevTools
```

---

## Next Steps

1. ✅ All services compiled successfully
2. ✅ Architecture configured with service discovery
3. ✅ API Gateway with global logging ready
4. **TODO:** Start services in sequence and verify:
   - Service registration in Eureka
   - Direct service endpoint access
   - API Gateway routing
   - Global request logging
