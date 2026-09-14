# Copilot Chat Conversation Export: API contracts for expense tracker backend

**User:** @Rishindra-Rao
**Thread URL:** https://github.com/copilot/c/f87ea9d4-62ec-4ca2-bc2a-ce20408dc9b4

## @Rishindra-Rao

Give me the detailed API contracts of this expense tracker backend written in JAVA + Spring Boot

## Copilot

I'll help you find the detailed API contracts for the expense tracker backend. Let me search for API-related files and contracts in the repository.Now let me get the complete controller files and DTO definitions:Now let me fetch the DTOs to provide complete API contract details:## Detailed API Contracts - Expense Tracker Backend

Here's a comprehensive breakdown of the API contracts for your Spring Boot Expense Tracker:

---

### **1. AUTHENTICATION ENDPOINTS** (`/auth`)

#### Register User
```http
POST /auth/register
Content-Type: application/json

Request Body:
{
  "name": "string" (required, not blank),
  "email": "string" (required, valid email),
  "password": "string" (required, not blank)
}

Response: 200 OK
{
  "id": "long",
  "name": "string",
  "email": "string",
  "password": "string (encrypted)"
}

Error Cases:
- 400: Validation errors (missing fields, invalid email)
- 409: Email already exists
```

#### Login User
```http
POST /auth/login
Content-Type: application/json

Request Body:
{
  "email": "string" (required, valid email),
  "password": "string" (required, not blank)
}

Response: 200 OK
{
  "token": "string (JWT token)"
}

Error Cases:
- 400: Validation errors
- 401: Invalid credentials
```

---

### **2. EXPENSE MANAGEMENT ENDPOINTS** (`/expenses`)

**Security:** Requires Bearer Token (JWT) in Authorization header  
**Base URL:** `GET/POST/PUT/DELETE /expenses`

#### Create Expense
```http
POST /expenses
Authorization: Bearer <token>
Content-Type: application/json

Request Body:
{
  "title": "string" (required, not blank),
  "amount": "number" (required, must be positive),
  "category": "FOOD|TRAVEL|SHOPPING|BILLS|ENTERTAINMENT|HEALTH|OTHER" (optional)
}

Response: 201 Created
{
  "id": "long",
  "title": "string",
  "amount": "number",
  "category": "enum",
  "expenseDate": "date",
  "createdAt": "datetime"
}

Error Cases:
- 400: Validation errors (missing title, negative amount)
- 401: Unauthorized (invalid/missing token)
```

#### Get All Expenses (Paginated)
```http
GET /expenses?page=0&size=5&sortBy=amount
Authorization: Bearer <token>

Query Parameters:
- page: int (default: 0)
- size: int (default: 5)
- sortBy: string (default: "amount")

Response: 200 OK
{
  "content": [
    {
      "id": "long",
      "title": "string",
      "amount": "number",
      "category": "enum",
      "expenseDate": "date",
      "createdAt": "datetime"
    }
  ],
  "totalElements": "long",
  "totalPages": "int",
  "currentPage": "int",
  "pageSize": "int"
}

Error Cases:
- 401: Unauthorized
```

#### Get Expense by ID
```http
GET /expenses/{id}
Authorization: Bearer <token>

Path Parameters:
- id: long (expense ID)

Response: 200 OK
{
  "id": "long",
  "title": "string",
  "amount": "number",
  "category": "enum",
  "expenseDate": "date",
  "createdAt": "datetime"
}

Error Cases:
- 404: Expense not found
- 401: Unauthorized
- 403: Not owner of expense
```

#### Filter Expenses by Category
```http
GET /expenses/category/{category}
Authorization: Bearer <token>

Path Parameters:
- category: enum (FOOD|TRAVEL|SHOPPING|BILLS|ENTERTAINMENT|HEALTH|OTHER)

Response: 200 OK
[
  {
    "id": "long",
    "title": "string",
    "amount": "number",
    "category": "enum",
    "expenseDate": "date",
    "createdAt": "datetime"
  }
]

Error Cases:
- 401: Unauthorized
- 400: Invalid category
```

#### Filter Expenses by Minimum Amount
```http
GET /expenses/amount/{amount}
Authorization: Bearer <token>

Path Parameters:
- amount: number (minimum expense amount)

Response: 200 OK
[
  {
    "id": "long",
    "title": "string",
    "amount": "number",
    "category": "enum",
    "expenseDate": "date",
    "createdAt": "datetime"
  }
]

Error Cases:
- 401: Unauthorized
```

#### Update Expense
```http
PUT /expenses/{id}
Authorization: Bearer <token>
Content-Type: application/json

Path Parameters:
- id: long (expense ID)

Request Body:
{
  "title": "string" (required, not blank),
  "amount": "number" (required, must be positive),
  "category": "enum" (optional)
}

Response: 200 OK
{
  "id": "long",
  "title": "string",
  "amount": "number",
  "category": "enum",
  "expenseDate": "date",
  "createdAt": "datetime"
}

Error Cases:
- 400: Validation errors
- 401: Unauthorized
- 403: Not owner of expense
- 404: Expense not found
```

#### Delete Expense
```http
DELETE /expenses/{id}
Authorization: Bearer <token>

Path Parameters:
- id: long (expense ID)

Response: 204 No Content

Error Cases:
- 401: Unauthorized
- 403: Not owner of expense
- 404: Expense not found
```

---

### **3. BUDGET MANAGEMENT ENDPOINTS** (`/budgets`)

**Security:** Requires Bearer Token (JWT)

#### Create Budget
```http
POST /budgets
Authorization: Bearer <token>
Content-Type: application/json

Request Body:
{
  "amount": "number" (required),
  "month": "JANUARY|FEBRUARY|...|DECEMBER" (required),
  "year": "integer" (required)
}

Response: 201 Created
{
  "id": "long",
  "amount": "number",
  "month": "enum",
  "year": "integer"
}

Error Cases:
- 400: Validation errors
- 401: Unauthorized
- 409: Budget already exists for this month/year
```

#### Get Current Budget
```http
GET /budgets/current
Authorization: Bearer <token>

Response: 200 OK
{
  "id": "long",
  "amount": "number",
  "month": "enum",
  "year": "integer"
}

Error Cases:
- 401: Unauthorized
- 404: No budget found for current month
```

#### Get Budget Status
```http
GET /budgets/status
Authorization: Bearer <token>

Response: 200 OK
{
  "budget": "number",
  "spent": "number",
  "remaining": "number",
  "percentageUsed": "integer (0-100)",
  "overspent": "boolean",
  "message": "string (user-friendly message)",
  "status": "SAFE|WARNING|EXCEEDED",
  "color": "GREEN|YELLOW|RED"
}

Error Cases:
- 401: Unauthorized
- 404: No budget found
```

#### Get Monthly Report by Category
```http
GET /budgets/reports/monthly
Authorization: Bearer <token>

Response: 200 OK
[
  {
    "category": "FOOD|TRAVEL|SHOPPING|BILLS|ENTERTAINMENT|HEALTH|OTHER",
    "totalAmount": "number"
  }
]

Error Cases:
- 401: Unauthorized
```

---

### **4. UTILITY ENDPOINTS**

#### Health Check
```http
GET /hello

Response: 200 OK
"Hello Expense Tracker"
```

---

### **DATA MODELS**

#### Category Enum
```
FOOD
TRAVEL
SHOPPING
BILLS
ENTERTAINMENT
HEALTH
OTHER
```

#### Expense Entity
```java
{
  id: Long,
  title: String,
  amount: Double,
  category: Category,
  expenseDate: LocalDate,
  createdAt: LocalDateTime,
  user: User (owner)
}
```

#### Budget Status Enum
```
SAFE
WARNING
EXCEEDED
```

---

### **SECURITY & VALIDATION**

- **Authentication:** JWT Bearer Token required for `/expenses` and `/budgets` endpoints
- **Authorization:** Users can only access/modify their own expenses and budgets
- **Validation:**
  - Email: Must be valid email format
  - Amount: Must be positive number
  - Title: Cannot be blank
  - Password: Cannot be blank

---

### **HTTP STATUS CODES**

| Code | Meaning |
|------|---------|
| 200 | Success (GET, PUT) |
| 201 | Created (POST) |
| 204 | No Content (DELETE) |
| 400 | Bad Request (validation errors) |
| 401 | Unauthorized (missing/invalid token) |
| 403 | Forbidden (access denied) |
| 404 | Not Found (resource doesn't exist) |
| 409 | Conflict (duplicate resource) |
| 500 | Internal Server Error |
