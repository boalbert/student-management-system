# JAVA EE / JAX-RS - Labb
## Komplex Java - JU2020



## Endpoints

* **GET - ONE** 

```java
/api/v1/students/{id}

// Response Code: 200 OK
```




* **GET - ALL** 

```java
/api/v1/students/

// Response Code: 200 OK
```



* **GET - BY LASTNAME**

```java
/api/v1/students/search?lastName={lastName}

// Example, Query Parameter
/api/v1/students/search?lastName=Persson

// Response Code: 200 OK
```



* **POST** 

```java
/api/v1/students

// Body
{
	"firstName" : "Sara", 
	"lastName" : "Persson", 
	"email" : "sarap@email.com",
	"phoneNumber" : "073548824"
}

// Response Code: 201 Created
// Header - Location: .../student-management-system/api/v1/students/1
```



* **PUT**

```java
/api/v1/students/{id}

// Body
{
	"firstName" : "Sara",
	"lastName" : "Andersson",
	"email" : "saraandersson@email.com",
	"phoneNumber" : "073548824"
}

// Response Code: 200 OK / 201 Created
// 201 Created: 
	// Header - Location: .../student-management-system/api/v1/students/1
```



* **PATCH**

```java
/api/v1/students/{id}

// Body
{
	"email" : "andersson@email.com"
}

// Response Code: 200 OK 
```



* **DELETE**

```java
/api/v1/students/{id}

// Response Code: 204 No content
```



## Constraints

```java
@Size(min = 2, message = "Min. 2 characters")
@NotEmpty(message = "Must not be empty")
private String firstName;

@Size(min = 2, message = "Min. 2 characters")
@NotEmpty(message = "Must not be empty")
private String lastName;

@Email(regexp = ".+@.+\\..+", message = "Not a valid email adress.")
@NotEmpty(message = "Must not be empty")
private String email;
```



## Exceptions

* **StudentNotFoundException** 
  * GET, PATCH


```java
// Response Code: 404 Not Found
{
  "error message": "Student not found.",
  "student id": 99
}
```



* **ConstraintViolationException**
  * POST, PUT, PATCH


```java
// Response Code: 400 Bad Request
[
  {
    "error message": "Must not be empty",
    "property": "firstName"
  },
  {
    "error message": "Not a valid email adress.",
    "property": "email"
  },
  {
    "error message": "Must be between 2 - 50 characters",
    "property": "lastName"
  },
  {
    "error message": "Must be between 2 - 50 characters",
    "property": "firstName"
  }
]
```
