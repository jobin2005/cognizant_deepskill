# Quick Reference Guide - Microservices Commands

## 📋 Quick Commands

### Build All Services
```bash
# From microservices directory
cd eureka-discovery-server && mvn clean compile
cd ../account && mvn clean compile
cd ../loan && mvn clean compile
cd ../api-gateway && mvn clean compile
```

### Run Services (in correct order - use separate terminals)

#### Terminal 1 - Eureka Server (REQUIRED FIRST)
```bash
cd microservices/eureka-discovery-server
mvn spring-boot:run
# Expected: Tomcat started on port(s): 8761
```

#### Terminal 2 - Account Service
```bash
cd microservices/account
mvn spring-boot:run
# Expected: Tomcat started on port(s): 8080
# Should see: Registration with Eureka successful
```

#### Terminal 3 - Loan Service
```bash
cd microservices/loan
mvn spring-boot:run
# Expected: Tomcat started on port(s): 8081
# Should see: Registration with Eureka successful
```

#### Terminal 4 - API Gateway
```bash
cd microservices/api-gateway
mvn spring-boot:run
# Expected: Tomcat started on port(s): 9090
# Should see: Discovery locator routes configured
```

---

## 🧪 Testing Endpoints

### Eureka Registry
```bash
# View all registered services
curl http://localhost:8761/eureka/apps

# View specific service
curl http://localhost:8761/eureka/apps/account-service
curl http://localhost:8761/eureka/apps/loan-service
curl http://localhost:8761/eureka/apps/api-gateway
```

### Direct Service Calls (No Gateway)

#### Account Service (Port 8080)
```bash
# Single account
curl http://localhost:8080/accounts/ACC00987987973432

# Try different account numbers
curl http://localhost:8080/accounts/ACC001
curl http://localhost:8080/accounts/ACC123
```

#### Loan Service (Port 8081)
```bash
# Single loan
curl http://localhost:8081/loans/L00987987972342

# Try different loan numbers
curl http://localhost:8081/loans/L001
curl http://localhost:8081/loans/L456
```

### Through API Gateway (Port 9090)

#### Account Service via Gateway
```bash
curl http://localhost:9090/account-service/accounts/ACC00987987973432
curl http://localhost:9090/account-service/accounts/ACC001
curl http://localhost:9090/account-service/accounts/ACC123
```

#### Loan Service via Gateway
```bash
curl http://localhost:9090/loan-service/loans/L00987987972342
curl http://localhost:9090/loan-service/loans/L001
curl http://localhost:9090/loan-service/loans/L456
```

---

## 📊 Expected Responses

### Account Response
```json
{
  "number": "ACC123",
  "type": "savings",
  "balance": 234343
}
```

### Loan Response
```json
{
  "number": "L456",
  "type": "car",
  "loanAmount": 400000,
  "emi": 3258,
  "tenure": 18
}
```

---

## 🔍 Troubleshooting

### Issue: "Cannot connect to Eureka"
```bash
# Make sure Eureka is running on port 8761
netstat -tlnp | grep 8761

# Or use curl to check
curl http://localhost:8761/eureka/apps
```

### Issue: "Services not showing in Eureka"
1. Check Eureka server is running first
2. Wait 30 seconds for registration
3. Check service logs for "Registering with Eureka"
4. Verify eureka.client.service-url.defaultZone in application.properties

### Issue: "API Gateway returning 503"
- All services must be registered in Eureka
- Service names must match (use lowercase in gateway URLs)
- Check gateway logs for discovery errors

### View Logs
```bash
# Check if service is running and listening
lsof -i :8080   # Account
lsof -i :8081   # Loan
lsof -i :8761   # Eureka
lsof -i :9090   # Gateway

# Kill service if needed
kill -9 <PID>
```

---

## 🔄 Complete Flow Test

### 1. Start Everything
```bash
# Terminal 1
cd microservices/eureka-discovery-server && mvn spring-boot:run

# Terminal 2 (after Eureka starts)
cd microservices/account && mvn spring-boot:run

# Terminal 3 (after Account registers)
cd microservices/loan && mvn spring-boot:run

# Terminal 4 (after Loan registers)
cd microservices/api-gateway && mvn spring-boot:run
```

### 2. Verify Registration
```bash
# Check Eureka dashboard
curl http://localhost:8761/eureka/apps | grep "APPLICATION"
```

### 3. Test Direct Services
```bash
curl http://localhost:8080/accounts/ACC123
curl http://localhost:8081/loans/L456
```

### 4. Test Gateway
```bash
curl http://localhost:9090/account-service/accounts/ACC123
curl http://localhost:9090/loan-service/loans/L456
```

### 5. Watch Console Logs
- **Account logs**: If request comes through account service
- **Loan logs**: If request comes through loan service
- **Gateway logs**: All requests logged with:
  - Request Method
  - Request Path
  - Request Headers

---

## 📝 Port Reference

| Service | Port | URL |
|---------|------|-----|
| **Eureka Server** | 8761 | http://localhost:8761/eureka |
| **Account Service** | 8080 | http://localhost:8080/accounts/{number} |
| **Loan Service** | 8081 | http://localhost:8081/loans/{number} |
| **API Gateway** | 9090 | http://localhost:9090/{service}/{endpoint} |

---

## 🚀 Advanced Testing

### Test with Different Data
```bash
# Account with different IDs
for i in {1..5}; do
  echo "Testing Account ACC$i"
  curl http://localhost:9090/account-service/accounts/ACC$i
done

# Loan with different IDs
for i in {1..5}; do
  echo "Testing Loan L$i"
  curl http://localhost:9090/loan-service/loans/L$i
done
```

### Monitor Requests
```bash
# Watch gateway logs for incoming requests
tail -f <gateway-console-output>

# Each request should show:
# Request Method: GET
# Request Path: /account-service/accounts/...
# Request Headers: {...}
```

### Load Test (using Apache Bench)
```bash
# Test Account through Gateway
ab -n 100 -c 10 http://localhost:9090/account-service/accounts/ACC123

# Test Loan through Gateway
ab -n 100 -c 10 http://localhost:9090/loan-service/loans/L456
```

---

## ✅ Success Criteria

- [ ] All 4 services start without errors
- [ ] Eureka shows 3 registered instances (Account, Loan, Gateway)
- [ ] Direct service calls work (ports 8080, 8081)
- [ ] Gateway routing works (port 9090)
- [ ] Gateway logs show incoming requests
- [ ] Can make 10+ requests with different parameters
- [ ] No errors in any console output

---

## 📚 Architecture Review

### Service Dependency
```
Eureka Server (8761)
    ↑ (registers with)
    ├── Account Service (8080)
    ├── Loan Service (8081)
    └── API Gateway (9090)
         ↑ (queries)
         └── Eureka Server (8761)
```

### Request Path
```
Client
  ↓
API Gateway (9090)
  ↓ (routes based on service name)
  ├→ Account Service (8080)
  └→ Loan Service (8081)
```

### Benefits of This Architecture
✅ Single entry point for all services  
✅ Dynamic service discovery (no hardcoded URLs)  
✅ Easy to add more services  
✅ Centralized logging via gateway  
✅ Services can be restarted independently  
✅ Better organization and scalability  
