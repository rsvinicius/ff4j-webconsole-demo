# FF4J - Web Console Demo

`ff4j-webconsole-demo` is a Kotlin project specifically designed to showcase the seamless integration of FF4J with a PostgreSQL JDBC event repository storage, emphasizing web console management and incorporating Spring Security for enhanced security measures.

## Description

This demo project illustrates the configuration of FF4J with a PostgreSQL database as the JDBC event repository storage. The decision to use PostgreSQL is driven by the need for audit functionality, dependent on event storage. The project also utilizes Redis for feature and properties stores.

This project concentrates solely on FF4J and not on authentication. Therefore, it employs an in-memory database for security, with credentials hardcoded in Spring Security config. It utilizes HTTP basic authentication for login.

## Requirements

- Java 17+
- IntelliJ IDEA / Netbeans / Eclipse
- Docker (and Docker Compose for convenient setup)

## Usage

1) Launch local PostgreSQL and Redis databases using Docker Compose:
   ```bash
    docker-compose up
    ```  
   Ensure your docker-compose.yml file includes the PostgreSQL and Redis services, and adapt the database configurations accordingly.

2) Start the application either in your IDE or via the command line:

    ```
    ./gradlew bootRun
    ```  

3) Access the web console at http://localhost:9097/ff4j-web-console/ and log in using the Spring Security credentials.