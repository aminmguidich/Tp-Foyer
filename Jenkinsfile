pipeline {
    agent any

    environment {
        SONARQUBE_SERVER = 'http://localhost:9000/'  // Update with your SonarQube server URL
        SONARQUBE_TOKEN = 'sqa_b150e6b1b031e1d5432732412a85cc0bfb9a5226'  // Use the credentials ID you set for SonarQube token
        SONAR_PROJECT_KEY = 'amin' // Replace with your actual project key
        NEXUS_URL = 'http://localhost:8081' // Your Nexus URL
        NEXUS_REPO = 'maven-releases' // Your Nexus repository name
        DOCKER_USERNAME = "aminmguidich"
        DOCKER_PASSWORD = "aminmguidich"

    }

    stages {
        stage('Checkout Code from GitHub') {
            steps {
                git branch: 'mguidich', url: 'https://github.com/aminmguidich/Tp-Foyer'
            }
        }

        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Mockito') {
            steps {
                echo "Running unit tests"
                sh 'mvn test'
            }
        }

        stage('Generate JaCoCo Coverage Report') {
            steps {
                echo "Generating JaCoCo XML coverage report"
                // This command runs tests and generates a JaCoCo XML report
                sh 'mvn test jacoco:report -Djacoco.reportPath=target/site/jacoco/jacoco.xml'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo "Running SonarQube analysis"
                // Run Sonar analysis with the specified JaCoCo XML report path
                sh "mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.host.url=${SONARQUBE_SERVER} -Dsonar.login=${SONARQUBE_TOKEN} -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml"
            }
        }

        stage('Maven Deploy to Nexus') {
            steps {
                echo "Deploying to Nexus"
                // Deploy to Nexus and skip tests, using the server ID from settings.xml
                sh """
                    mvn -s /usr/share/maven/conf/settings.xml deploy \
                    -DskipTests \
                    -DaltDeploymentRepository=deploymentRepo::default::${NEXUS_URL}/repository/${NEXUS_REPO}/
                """
            }
        }
         stage('Build image') {
            steps {
                sh "docker build -t tpfoyer_image ."
            }
         }
          stage('Push Docker Image') {
           steps {
              withCredentials([usernamePassword(credentialsId: 'docker_token', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                sh "docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD}"
                sh "docker tag tpfoyer_image ${DOCKER_USERNAME}/tpfoyer_image:latest"
                sh "docker push ${DOCKER_USERNAME}/tpfoyer_image:latest"
              }
           }
        }

        stage('Docker compose') {
            steps {
                sh "docker-compose up -d"
            }
        }

    }
    post {
        always {
            script {
                def jobName = env.JOB_NAME
                def buildNumber = env.BUILD_NUMBER
                def pipelineStatus = currentBuild.result ?: 'SUCCESS'  // Default to 'SUCCESS' if no result
                def bannerColor = (pipelineStatus == 'SUCCESS') ? 'green' : 'red'

                // Construct the HTML email body
                def emailBody = """
                <html>
                <body>
                    <div style="border: 4px solid ${bannerColor}; padding: 10px;">
                        <h2>${jobName} - Build ${buildNumber}</h2>
                        <div style="background-color: ${bannerColor}; padding: 10px;">
                            <h3 style="color: white;">Pipeline Status: ${pipelineStatus}</h3>
                        </div>
                        <p>For details, check the <a href="${BUILD_URL}">console output</a>.</p>
                    </div>
                </body>
                </html>"""

                // Send email with pipeline status
                emailext (
                    subject: "${jobName} - Build ${buildNumber} - ${pipelineStatus}",
                    body: emailBody,
                    to: 'medamine.mguidich@gmail.com',
                    from: 'mguidich46@gmail.com',
                    replyTo: 'medamine.mguidich@gmail.com',
                    mimeType: 'text/html'
                    // Optionally, you can add attachments here if needed
                )
            }
        }
    }
}
