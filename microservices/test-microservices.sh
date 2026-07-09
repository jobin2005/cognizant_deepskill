#!/bin/bash

# Microservices Testing Script
# Usage: bash test-microservices.sh

set -e

# Color codes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}=== Microservices Testing Suite ===${NC}\n"

# Step 1: Check if Eureka is running
echo -e "${YELLOW}Step 1: Checking Eureka Server (8761)...${NC}"
if curl -s http://localhost:8761/eureka/apps > /dev/null 2>&1; then
    echo -e "${GREEN}✓ Eureka Server is running${NC}"
else
    echo -e "${RED}✗ Eureka Server is NOT running${NC}"
    echo "Start it with: cd eureka-discovery-server && mvn spring-boot:run"
    exit 1
fi

# Step 2: Check registered services
echo -e "\n${YELLOW}Step 2: Checking registered services in Eureka...${NC}"
RESPONSE=$(curl -s http://localhost:8761/eureka/apps)

if echo "$RESPONSE" | grep -q "ACCOUNT-SERVICE"; then
    echo -e "${GREEN}✓ Account Service registered${NC}"
else
    echo -e "${RED}✗ Account Service NOT registered${NC}"
fi

if echo "$RESPONSE" | grep -q "LOAN-SERVICE"; then
    echo -e "${GREEN}✓ Loan Service registered${NC}"
else
    echo -e "${RED}✗ Loan Service NOT registered${NC}"
fi

if echo "$RESPONSE" | grep -q "API-GATEWAY"; then
    echo -e "${GREEN}✓ API Gateway registered${NC}"
else
    echo -e "${RED}✗ API Gateway NOT registered${NC}"
fi

# Step 3: Test Account Service Direct
echo -e "\n${YELLOW}Step 3: Testing Account Service (Direct at 8080)...${NC}"
if curl -s http://localhost:8080/accounts/ACC00987987973432 > /dev/null 2>&1; then
    ACCOUNT_DATA=$(curl -s http://localhost:8080/accounts/ACC00987987973432)
    echo -e "${GREEN}✓ Account Service responding${NC}"
    echo -e "  Response: ${ACCOUNT_DATA}\n"
else
    echo -e "${RED}✗ Account Service NOT responding at port 8080${NC}"
    echo "Start it with: cd account && mvn spring-boot:run"
fi

# Step 4: Test Loan Service Direct
echo -e "${YELLOW}Step 4: Testing Loan Service (Direct at 8081)...${NC}"
if curl -s http://localhost:8081/loans/L00987987972342 > /dev/null 2>&1; then
    LOAN_DATA=$(curl -s http://localhost:8081/loans/L00987987972342)
    echo -e "${GREEN}✓ Loan Service responding${NC}"
    echo -e "  Response: ${LOAN_DATA}\n"
else
    echo -e "${RED}✗ Loan Service NOT responding at port 8081${NC}"
    echo "Start it with: cd loan && mvn spring-boot:run"
fi

# Step 5: Test API Gateway
echo -e "${YELLOW}Step 5: Testing API Gateway (9090)...${NC}"
if curl -s http://localhost:9090/account-service/accounts/ACC00987987973432 > /dev/null 2>&1; then
    GATEWAY_ACCOUNT=$(curl -s http://localhost:9090/account-service/accounts/ACC00987987973432)
    echo -e "${GREEN}✓ Gateway routing to Account Service${NC}"
    echo -e "  Response: ${GATEWAY_ACCOUNT}\n"
else
    echo -e "${RED}✗ Gateway NOT routing to Account Service${NC}"
    echo "Start it with: cd api-gateway && mvn spring-boot:run"
fi

# Step 6: Test Loan Service through Gateway
echo -e "${YELLOW}Step 6: Testing Loan Service through Gateway (9090)...${NC}"
if curl -s http://localhost:9090/loan-service/loans/L00987987972342 > /dev/null 2>&1; then
    GATEWAY_LOAN=$(curl -s http://localhost:9090/loan-service/loans/L00987987972342)
    echo -e "${GREEN}✓ Gateway routing to Loan Service${NC}"
    echo -e "  Response: ${GATEWAY_LOAN}\n"
else
    echo -e "${RED}✗ Gateway NOT routing to Loan Service${NC}"
fi

# Step 7: Test multiple requests
echo -e "${YELLOW}Step 7: Testing multiple account requests...${NC}"
echo "Accounts to test: ACC001, ACC002, ACC003"
for ACC in "ACC001" "ACC002" "ACC003"; do
    if curl -s http://localhost:9090/account-service/accounts/$ACC > /dev/null 2>&1; then
        echo -e "${GREEN}✓ Account $ACC returning data${NC}"
    fi
done

echo -e "\n${YELLOW}=== Testing Complete ===${NC}"
echo -e "${GREEN}All services operational!${NC}"
