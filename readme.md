# AlbumManager API

## Overview

The AlbumManager API is a Java-based backend service designed to support the creation and management of personal photo albums and digital images. It leverages Spring Boot for the backend framework, Hibernate for ORM, Maven for project management, and integrates JWT authentication for security. This RESTful API allows for the organized upload and storage of images, offering both original and thumbnail sizes to optimize user experience.

## Key Features

- **Secure Authentication and Authorization**: Utilizes JWT for robust security measures, protecting user data and preventing unauthorized access.
- **Image Upload and Storage**: Supports uploading images to specific albums, storing them in both original and thumbnail sizes.
- **Dynamic Album Creation**: Enables users to create and manage multiple albums for better organization.
- **REST API with Swagger UI**: Provides intuitive API documentation and testing capabilities through Swagger UI.
- **H2 Database Integration**: Employs the lightweight H2 Database for efficient data storage and retrieval.

## Technical Stack

- **Backend Framework**: Spring Boot
- **ORM Technology**: Hibernate
- **Project Management Tool**: Maven
- **Authentication**: JWT
- **API Documentation and Testing**: Swagger UI
- **Database**: H2 Database

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Maven 3.6.3 or later
- VS Code
- Spring Boot Extention for VS code

### Running the Application on VS Code

1. Clone the repository:
   ```sh
   git clone <repository-url>
2. Navigate to 
    ``` 
    src\main\java\org\studyeasy\SpringRest\SpringRestApplication.java
3. Right click -> Run java.
4. Open URL
    ```
    localhost:8080
4. Accessing Swagger UI 
    ```
    http://localhost:8080/swagger-ui/index.html
### Usage
After authenticating and obtaining a JWT token, you can use the provided endpoints to create albums, upload images, and manage your content.

### ScreenShots
![Screenshot 2024-06-26 143302](https://github.com/siddharth1201/Album-RestApi/assets/76205521/a309daa4-3c1e-4d8e-a2ea-0ca512f6d4b4)

![Screenshot 2024-06-26 143945](https://github.com/siddharth1201/Album-RestApi/assets/76205521/37bfa949-8d74-4ea7-8ace-844d66e06466)


