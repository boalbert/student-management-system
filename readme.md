# JAVA EE / JAX-RS - Labb
## Komplex Java - JU2020



## Endpoints

* **GET - ONE** 

```java
// Endpoint
/api/v1/students/{id}

// Response Code: 200 OK
```


* **GET - ALL** 

```java
// Endpoint
/api/v1/students/

// Response Code: 200 OK
```



* **GET - BY LASTNAME**

```java
// Endpoint
/api/v1/students/search?lastName={lastName}

// Example
/api/v1/students/search?lastName=Persson

// Response Code: 200 OK
```



* **POST** 

```java
// Endpoint
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
// Endpoint
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
// Endpoint
/api/v1/students/{id}

// Body
{
	"email" : "andersson@email.com"
}

// Response Code: 200 OK 
```



* **DELETE**

```java
// Endpoint
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

```java
// GET, PATCH

// Response Code: 404 Not Found
{
  "error message": "Student not found.",
  "student id": 99
}
```



* **ConstraintViolationException**

```java
// POST, PUT, PATCH

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



## Instructions / Instruktiner

- [x] Forka startprojekt från https: //github.com/pontusredig-alten/student-management-system
- CRUD-funktionalitet ska implementeras
    - [X] Create
    - [X] Read
    - [X] Update
    - [X] Delete
- [x] Data om en student ska kunna hämtas med efternamn som en Query Parameter
- [x] När en ny student ska läggas till, är alla fält obligatoriska utom telefonnummer
- [x] Anropen ska returnera meningsfulla Response Codes
- [x] Skapa minst en egen exception
- [x] Felhantering ska finnas för varje CRUD-metod, och felmeddelande ska returneras i JSON-format

______________

- Ni kan jobba i grupp om ni vill, max två personer per grupp.
- Lämna in labben genom en länk till erat repository på GitHub / GitLab.
- Jag kommer testa eran lösning med Insomnia, så skapa en README.md-fil i erat repository, där ni beskriver endpoints,
  parametrar och JSON-bodies ser ut.
- README.md-filen ska också innehålla kort beskrivning om vem som gjort vad (ifall ni jobbat i grupp), samt om ni stötte
  på problem som ni inte förstod hur man ska lösa.
- Konfigurationsfilerna ska ni inte behöva göra något med, utan det ska gå att koppla in Payara Server på samma sätt som
  vi gjort tidigare i kursen.
- Labbens sluttid är den 19/11 kl. 23:59.
