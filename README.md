# Tp-Foyer DevOps Project

This repository contains a DevOps pipeline for the Tp-Foyer project. The pipeline includes various stages such as building, testing, quality assurance, and deployment. This README provides an overview of the pipeline steps and includes relevant screenshots.

## Table of Contents

- [Pipeline Stages](#pipeline-stages)
- [Screenshots](#screenshots)
- [How to Run the Pipeline](#how-to-run-the-pipeline)
- [Tools Used](#tools-used)
- [Contributing](#contributing)

## Pipeline Stages

1. **Retrieve the project from GitHub/GitLab**
   - Clone the repository from GitHub.

2. **Create the project deliverable**
   - Build the project using Maven or Gradle.

3. **Run unit tests**
   - Execute unit tests to ensure code quality.

4. **Run code quality tests (SonarQube)**
   - Analyze the code using SonarQube for code quality checks.

5. **Deploy the deliverable to Nexus**
   - Upload the built artifacts to Nexus repository.

6. **Build the Docker image (Spring part)**
   - Create a Docker image for the Spring application.

7. **Create the Spring deliverable from DockerFile**
   - Build the Docker image using the DockerFile.

8. **Upload the created image to DockerHub**
   - Push the Docker image to DockerHub.

9. **Simultaneously launch the Spring deliverable image and MySQL image using docker-compose**
   - Use docker-compose to start both the Spring application and MySQL database.

10. **Test the application's services with Postman**
    - Validate the application's endpoints and services using Postman.

11. **Monitor tools (Jenkins, Spring application, etc.) using Prometheus and Grafana**
    - Set up monitoring for Jenkins and the Spring application with Prometheus and Grafana.

## Screenshots

Here are some screenshots from the project:

### Screenshot 1
![Screenshot1](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot1.png)

### Screenshot 2
![Screenshot2](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot2.png)

### Screenshot 3
![Screenshot3](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot3.png)

### Screenshot 4
![Screenshot4](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot4.png)

### Screenshot 5
![Screenshot5](https://github.com/aminmguidich/Tp-Foyer/blob/main/docs/Screenshots/Screenshot5.png)

## How to Run the Pipeline

1. Clone this repository:
   ```bash
   git clone https://github.com/aminmguidich/Tp-Foyer.git
   cd Tp-Foyer
