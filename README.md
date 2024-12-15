# **Tp-Foyer DevOps Pipeline**

This repository contains the Spring Boot application setup with Docker and Docker Compose. The project ensures seamless containerization and deployment of the application and its MySQL database.

---

## **Table of Contents**
1. [Overview](#overview)
2. [Project Structure](#project-structure)
3. [Tools & Technologies Used](#tools--technologies-used)
4. [How to Build and Run](#how-to-build-and-run)
5. [Screenshots](#screenshots)
6. [Monitoring](#monitoring)
7. [Contributing](#contributing)

---

## **Overview**

The Tp-Foyer project uses Docker and Docker Compose to containerize the Spring Boot application and MySQL database. The goal is to simplify deployment while maintaining scalability and modularity.

### **Key Features:**
- Spring Boot REST API.
- MySQL database integration.
- Containerized environment using Docker.
- Multi-container orchestration using Docker Compose.

---

## **Project Structure**

The project is organized as follows:

```plaintext
Tp-Foyer/
├── src/                  # Spring Boot source code
├── Dockerfile            # Instructions to build the Docker image
├── docker-compose.yml    # Multi-container setup (Spring Boot + MySQL)
└── README.md             # Documentation
```

---

## **Tools & Technologies Used**

- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **Containerization**: Docker
- **Orchestration**: Docker Compose

---

## **How to Build and Run**

Follow these steps to build and run the application:

### **1. Prerequisites**
Ensure the following tools are installed on your system:
- Docker
- Docker Compose

### **2. Build the Docker Image**
Run the following command in the project root directory:

```bash
docker build -t tp-foyer-app .
```

### **3. Run the Application with Docker Compose**
Use Docker Compose to start both the Spring Boot application and MySQL database:

```bash
docker-compose up
```

### **4. Access the Application**
- The Spring Boot application will be accessible at: [http://localhost:8080](http://localhost:8080)
- MySQL will be running on port `3306`.

### **5. Stop the Containers**
To stop and remove containers, run:

```bash
docker-compose down
```

---

## **Screenshots**

### **1. Docker Compose Execution**
![Docker Compose](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot1.png)

### **2. Application Running in Docker**
![Docker Containers](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot2.png)

---

## **Monitoring**

You can integrate tools like **Prometheus** and **Grafana** to monitor the application's performance, resource usage, and health metrics.

---

## **Contributing**

Contributions are welcome! Follow these steps:
1. Fork the repository.
2. Create a new branch.
3. Commit and push your changes.
4. Submit a pull request.

For major changes, please open an issue first to discuss the proposed updates.

---

**Contact**: For questions, reach out to the repository maintainer.
