<a name="readme-top"></a>

# 📗 Table of Contents

- [📖 About the Project](#about-project)
  - [🛠 Built With](#built-with)
    - [Tech Stack](#tech-stack)
    - [Key Features](#key-features)
- [💻 Getting Started](#getting-started)
  - [Setup](#setup)
  - [Prerequisites](#prerequisites)
  - [Run](#run)
  - [Usage](#usage)
  - [Run tests](#run-tests)
  - [Deployment](#deployment)
- [👥 Authors](#authors)
- [🔭 Future Features](#future-features)
- [🤝 Contributing](#contributing)
- [⭐️ Show your support](#support)
- [🙏 Acknowledgements](#acknowledgements)
- [📝 License](#license)

# 📖 Trading Java Layer <a name="about-project"></a>

**[Trading Java Layer]**
This project represents the Java layer of the Trading-App Stack, serving as the main backend API and security gateway of the system. It provides a unified access layer to the database, manages authentication and authorization, and coordinates communication between the frontend and the Python analytics service.

The Java layer is built with Spring Boot and containerized using Docker, following a DevOps CI/CD pipeline for automated deployment. It ensures stable API exposure, secure token validation, and smooth integration with the rest of the trading stack.

This service plays a central role in maintaining data integrity, scalability, and access control across the entire system architecture.

Other repos on the App Stack for this project:
- [Trading App Front-End](https://github.com/alexansaa/TradingAppFrontEnd)
- [Trading App Python Layer](https://github.com/alexansaa/TradingPythonLayer)
- [Trading App Database](https://github.com/alexansaa/Trading-db)

## 🛠 Built With <a name="built-with"></a>

### Tech Stack <a name="tech-stack"></a>

<details>
  <summary>Core Technologies</summary>
  <ul>
    <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
    <li><a href="https://learn.microsoft.com/en-us/azure/active-directory/develop/">MSAL / Azure Entra ID</a></li>
    <li><a href="https://www.docker.com/">Docker</a></li>
    <li><a href="https://azure.microsoft.com/es-es/products/devops">Azure DevOps</a></li>
  </ul>
</details>

### Key Features <a name="key-features"></a>

- 🔐 **Authentication & Authorization** Integrates with Azure Entra ID and Google Federation using MSAL to secure API access with OAuth2/JWT.
- ⚙️ **REST API Gateway** Provides a secure communication layer between the frontend and backend services.
- 💾 **Database Access Layer** Connects to the SQL Server database and exposes structured endpoints for market and user data.
- 🔁 **Python Layer Integration** Acts as middleware between the frontend and Python service, routing analytics and forecasting requests.
- 🐳 **Containerized Deployment** Runs as a Dockerized service, designed to integrate seamlessly into the multi-container environment (trading-core network).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 💻 Getting Started <a name="getting-started"></a>

To get a local copy up and running, follow these steps.

### Prerequisites

- Java 21+ (compatible with Spring Boot 3.x)
- Docker & Docker Compose (for containerized deployment)
- Git (to clone the repository and manage source control)
- SQL Server instance running within the same Docker network (trading-core)
- Azure DevOps Agent (if deploying via CI/CD pipeline)
- Azure Entra ID App Registration (for MSAL authentication)

### Setup

Clone this repository to your desired folder:

```sh
  git clone https://github.com/alexansaa/TradingJavaLayer.git
  cd TradingJavaLayer
```
Ensure the shared network exists. The Python container must communicate with the SQL container through the shared Docker network:

```sh
  docker network create trading-core || true
```

### Run

To run locally (without Docker):

```sh
  mvn spring-boot:run
```
To build and run as a Docker container:

```sh
  docker compose up --build -d
```
Verify API is running:

```sh
  curl http://localhost:8080/api/health
```
Expected response:

```json
  {"status": "ok"}
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- AUTHORS -->

## 👥 Authors <a name="authors"></a>

👤 **Alexander**

- GitHub: [https://github.com/alexansaa](https://github.com/alexansaa)
- LinkedIn: [https://www.linkedin.com/in/alexander-saavedra-garcia/](https://www.linkedin.com/in/alexander-saavedra-garcia/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- FUTURE FEATURES -->

## 🔭 Future Features <a name="future-features"></a>

- [ ] 🔒 **[Role-Based Access Control (RBAC)]** for user groups and admin permissions.
- [ ] 🌐 **[Cross-Layer Request Authorization]** between Java and Python services.
- [ ] 📈 **[Enhanced API Analytics]** and request logging.
- [ ] ⚡ **[Reactive Endpoints]** for real-time data relay from the Python layer.
- [ ] ☁️ **[Kubernetes Deployment Support]** for horizontal scaling and load balancing.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->

## 🤝 Contributing <a name="contributing"></a>

Contributions, issues, and feature requests are welcome!

Feel free to check the [issues page](https://github.com/alexansaa/TradingJavaLayer/issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## ⭐️ Show your support <a name="support"></a>

If you like this project, please give it a star on GitHub

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## 🙏 Acknowledgments <a name="acknowledgements"></a>

I’d like to thank my wife for her patience and unwavering support during my darkest and most isolated days, when completing systems like these demanded every bit of my time, focus, and perseverance

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->

## 📝 License <a name="license"></a>

This project is licensed under the [GNU](./LICENSE.md) General Public License.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
