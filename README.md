#  Library Management System API

This project is a **Library Management System** jpp built using **Spring Boot**, **Spring Security**, and **Flyway** for database migrations.  
It provides an authentication system where **owners** can **add and delete books**, while **clients** can **view, borrow, and return books**.

---

##  API Endpoints and cURL Commands

### 1. Add a Book
####  **Only Owners can add books**
```sh
curl -u owner:owner123 -X POST http://localhost:8080/api/books/add -H "Content-Type: application/json" -d '{
"title": "Book1",
"author": "Author1",
"totalCopies": 5,
"availableCopies":5
}'
```

####  **Clients are not allowed to add books**
```sh
curl -u client:client123 -X POST http://localhost:8080/api/books/add -H "Content-Type: application/json" -d '{
"title": "Book1",
"author": "Author1",
"totalCopies": 5,
"availableCopies":5
}'
```

####  **Add multiple books using a loop**
```sh
for i in {1..10}; do
curl -u owner:owner123 -X POST http://localhost:8080/api/books/add \
-H "Content-Type: application/json" \
-d "{
\"title\": \"Book Title $i\",
\"author\": \"Author $i\",
\"totalCopies\": $i,
\"availableCopies\": $i
}"
echo ""
done
```

---

## 2.  View Books

####  **Clients can view available books**
```sh
curl -u client:client123 -X GET http://localhost:8080/api/books
```

####  **Owners can view all books**
```sh
curl -u owner:owner123 -X GET http://localhost:8080/api/books
```

---

## 3.  Borrow a Book
#### **Clients can borrow books**
```sh
curl -u client:client123 -X POST http://localhost:8080/api/books/borrow/1
```

####  **Owners cannot borrow books**
```sh
curl -u owner:owner123 -X POST http://localhost:8080/api/books/borrow/1  #  Not allowed
```

---

## 4. Return a Book
####  **Clients can return borrowed books**
```sh
curl -u client:client123 -X POST http://localhost:8080/api/books/return/1
```

####  **Owners cannot return books**
```sh
curl -u owner:owner123 -X POST http://localhost:8080/api/books/return/1  # Not allowed
```

---

## 5.  Delete a Book
####  **Clients cannot delete books**
```sh
curl -u client:client123 -X DELETE http://localhost:8080/api/books/delete/1  #  Not allowed
```

#### **Only Owners can delete books**
```sh
curl -u owner:owner123 -X DELETE http://localhost:8080/api/books/delete/1
```