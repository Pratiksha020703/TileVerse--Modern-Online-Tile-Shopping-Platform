# TileVerse – Auth Service

This document describes the **Auth Service** built so far in the TileVerse microservices ecommerce project.

---

## 📌 Overview

The **Auth Service** is a standalone microservice responsible for:

* User registration
* User login
* Password hashing
* JWT token generation
* Persistent user storage using MySQL

It is built using **ASP.NET Core Web API** and follows **microservice best practices**.

---

## 🧱 Architecture (So Far)

```
Client (Postman / React)
        ↓
Auth Service (.NET)
        ↓
MySQL Database (auth_db)
```

* Stateless authentication using JWT
* Database-backed user persistence

---

## 🛠️ Tech Stack

### Backend

* **ASP.NET Core Web API**
* **C#**
* **Entity Framework Core (EF Core)**

### Database

* **MySQL 8.x**

### Security

* **BCrypt** – password hashing
* **JWT (JSON Web Token)** – authentication

---

## 📦 NuGet Dependencies Used

These packages are explicitly installed and version-aligned:

```xml
<PackageReference Include="Microsoft.EntityFrameworkCore" Version="9.0.2" />
<PackageReference Include="Microsoft.EntityFrameworkCore.Relational" Version="9.0.2" />
<PackageReference Include="Microsoft.EntityFrameworkCore.Design" Version="9.0.2" />
<PackageReference Include="Pomelo.EntityFrameworkCore.MySql" Version="9.0.0" />
<PackageReference Include="System.IdentityModel.Tokens.Jwt" />
<PackageReference Include="Microsoft.IdentityModel.Tokens" />
<PackageReference Include="BCrypt.Net-Next" />
```

> ⚠️ EF Core and Pomelo versions must match to avoid runtime errors.

---

## 🗂️ Folder Structure

```
auth-service/
├── Controllers/
│   └── AuthController.cs
├── DTOs/
│   ├── LoginRequest.cs
│   └── RegisterRequest.cs
├── Models/
│   └── User.cs
├── Services/
│   └── JwtService.cs
├── Data/
│   └── AuthDbContext.cs
├── appsettings.json
├── Program.cs
└── AuthService.csproj
```

---

## 🧾 Database Schema (Current)

### USERS Table

| Column       | Type     | Description            |
| ------------ | -------- | ---------------------- |
| Id           | int (PK) | User ID                |
| FullName     | varchar  | User full name         |
| Email        | varchar  | Unique user email      |
| PasswordHash | varchar  | BCrypt hashed password |
| Role         | varchar  | CUSTOMER / ADMIN       |
| CreatedAt    | datetime | Account creation time  |

---

## 🔐 JWT Configuration

Configured in `appsettings.json`:

```json
"Jwt": {
  "Key": "<your-secret-key>",
  "Issuer": "TileVerse.Auth",
  "Audience": "TileVerse.Client"
}
```

* `Key` is generated manually (secret)
* Used for signing JWT tokens

---

## 🔌 API Endpoints

### 🔍 Health Check

```
GET /api/auth/health
```

Response:

```
Auth Service is UP
```

---

### 📝 Register

```
POST /api/auth/register
```

Request Body:

```json
{
  "fullName": "Pratiksha Mahajan",
  "email": "pratiksha@gmail.com",
  "password": "Test@123"
}
```

---

### 🔑 Login

```
POST /api/auth/login
```

Request Body:

```json
{
  "email": "pratiksha@gmail.com",
  "password": "Test@123"
}
```

Response:

```json
{
  "token": "<JWT_TOKEN>"
}
```

---

## ▶️ How to Run (Local)

### Step 1: Start MySQL

Ensure MySQL is running and the database exists:

```sql
CREATE DATABASE auth_db;
```

### Step 2: Configure Connection String

Edit `appsettings.json`:

```json
"ConnectionStrings": {
  "AuthDb": "server=localhost;port=3306;database=auth_db;user=root;password=YOUR_PASSWORD"
}
```

### Step 3: Restore Dependencies

```bash
dotnet restore
```

### Step 4: Run the Auth Service

```bash
dotnet run
```

Service runs at:

```
http://localhost:5000
```

```

---

## 🧭 Development Steps Followed (Till Now)

1. **Project Initialization**
   - Created root project structure for microservices
   - Initialized Git repository

2. **Auth Service Setup (.NET)**
   - Created ASP.NET Core Web API project
   - Structured folders (Controllers, DTOs, Models, Services, Data)

3. **JWT Authentication Implementation**
   - Implemented registration and login APIs
   - Added JWT token generation using secret key
   - Used `IConfiguration` for environment-based config

4. **Password Security**
   - Integrated BCrypt for hashing and verifying passwords

5. **Database Integration**
   - Integrated MySQL using EF Core
   - Created `AuthDbContext`
   - Persisted users in database
   - Auto-created tables using `EnsureCreated()` (development only)

6. **Testing & Validation**
   - Tested APIs using Postman
   - Verified persistence across restarts

---

## 🧠 Key Design Decisions

- Used **JWT** for stateless authentication
- Used **BCrypt** for secure password hashing
- Separate **Auth DB** for microservice isolation
- Used **EF Core** with MySQL for persistence
- Config-driven secrets using `IConfiguration`

---

## 🚧 Current Limitations (Planned Improvements)

- No refresh tokens yet
- No role-based authorization middleware
- No Dockerization yet
- No email/OTP verification yet

---

## 🔜 Next Planned Steps

- Dockerize Auth Service
- Secure Spring Boot Ecommerce APIs using JWT
- Role-based access control
- Centralized API Gateway

---

## 👩‍💻 Author

**Pratiksha Mahajan**  
TileVerse – Microservices Ecommerce Project

---

✅ Auth Service with MySQL + JWT + BCrypt is fully functional.

```
