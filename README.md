## Overview

The Expense Manager Application is designed to help organizations track employee income and expenses efficiently. Users can input their income and expenses, categorize them, and manage their financial data effortlessly. This application is built using Spring Boot, Hibernate, and MySQL, providing robust CRUD operations for expense tracking.


---


## Features

* User Management: Create and manage users.

* Income & Expense Tracking: Add, view, and filter income and expenses.

* Filtering Capabilities:
  1. Filter income and expenses by Day, Month, and Year.
  2. Filter by Income Type (Bonus, Salary, Consultancy, etc.).
  3. Filter by Expense Type (Food, Education, Bills, Travel, Miscellaneous, etc.).

* Data Persistence: Uses MySQL for data storage.

* REST API Endpoints for seamless integration.


---


## Tech Stack

* Spring Boot 2.7.7

* Hibernate (JPA for ORM)

* MySQL (Database)

* Postman (For API testing)


---


## Flowchart

The application follows a structured workflow:

1. Register User → 2. Add Income → 3. Add Expense → 4. Manage Expense


---


## API Endpoints

### User Endpoints

| Method       | Endpoint        | Description                      |
| ------------ |:----------------|:---------------------------------|
| GET          | /users/allUsers | Retrieves the list of all users  |
| GET          | /users/{id}     | Fetches a user by their ID       |
| POST         | /users/save     | Saves a new user to the database |


### Income Endpoints

| Method       | Endpoint               | Description                             |
| ------------ |:-----------------------|:----------------------------------------|
| GET          | /incomes/{incomeid}    | Retrieves income by its ID              |
| POST         | /incomes/save/{userId} | Registers new income for a given userId |


### Expense Endpoints

| Method       | Endpoint                  | Description                                  |
| ------------ |:--------------------------|:---------------------------------------------|
|POST          | /expenses/save/{incomeId} | Creates a new expense for the given incomeId |
