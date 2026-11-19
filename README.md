# Library Management REST API (Spring Boot)

**Course:** CS336B - Object Oriented Programming with Java (MSE-2 Exam)  
**Student Name:** Mayank Singh  
**University Roll No:** 202501100300154  
**Branch & Section:** CSE-AI, Section C  
**Course Outcome:** CO5 (Q2)  
**Server Port:** `8082`  

---

## 📌 Problem Statement
A college library wants to develop a **Library Management REST API** using Spring Boot to manage its book records. Each book record contains the following fields:
- `id` — `Integer`
- `title` — `String`
- `author` — `String`
- `category` — `String`
- `availableCopies` — `Integer`

---

## 🚀 REST API Endpoints

| HTTP Method | Endpoint | Description | Status Code |
|---|---|---|:---:|
| `POST` | `/books` or `/api/books` | Add a new book (with `@RequestBody`) | `201 Created` |
| `GET` | `/books` or `/api/books` | Retrieve all books | `200 OK` |
| `GET` | `/books/{id}` | Retrieve book by ID | `200 OK` / `404 Not Found` |
| `PUT` | `/books/{id}` | Update book details | `200 OK` / `404 Not Found` |
| `DELETE` | `/books/{id}` | Delete book by ID | `200 OK` / `404 Not Found` |

---

## 📦 Collections Used & Viva Business Logic
- **`ConcurrentHashMap<Integer, Book>`**: Provides thread-safe, in-memory collection storage with $O(1)$ constant time complexity for retrieval by book ID.
- **`ArrayList<Book>`**: Returned for bulk book listing.
- **`Optional<Book>`**: Handled safely to avoid `NullPointerException` when querying books by ID.

---

## 💻 How to Run Locally
```bash
mvn clean spring-boot:run
```
The server will start on `http://localhost:8082`.
