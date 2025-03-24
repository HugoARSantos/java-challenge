Java Challenge  
This repository contains the code for the Java challenge.  
The application is a REST API that performs mathematical operations with two operands and returns the value through an endpoint.

Requirements  
To run this project, you need:  
- Java 17 or higher.  
- Docker (to run the application).  
- Maven (to compile and run the project).

Installation of Requirements  
1. Java 17  
If you haven't installed Java 17 yet, follow the instructions for your platform.

Windows:  
- Download the JDK 17 from the official Oracle website.  
- During installation, make sure to select the option to add Java to the PATH automatically. If you didn't do this, you can add it manually as follows:  
  Add Java to PATH:  
  - Right-click on "This PC" or "My Computer" and select "Properties".  
  - Click on "Advanced System Settings", then "Environment Variables".  
  - In the "System Variables" section, select the Path variable and click "Edit".  
  - Add the path where the JDK was installed, usually something like `C:\Program Files\AdoptOpenJDK\jdk-17.0.1.12\bin` (check the correct path for your JDK).  
  - Click "OK" to save the changes.

Linux (Ubuntu/Debian):  
To install OpenJDK 17, run the following commands in the terminal:  
`sudo apt update`  
`sudo apt install openjdk-17-jdk`

macOS (with Homebrew):  
If you use Homebrew, run the following command:  
`brew install openjdk@17`  
After installation, if necessary, you can add Java 17 to the PATH with the following commands:  
`sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk`  
`export PATH="/usr/local/opt/openjdk@17/bin:$PATH"`

2. Docker  
If you wish to use Docker, follow these instructions:

Windows / Linux / macOS:  
- Download Docker Desktop from docker.com.  
- Install and run Docker on your machine.

3. Maven  
Maven is required to compile the project. If you don’t have Maven installed, follow these instructions:

Windows:  
- Download Maven from maven.apache.org.  
- Extract the `.zip` file.  
- Add the `bin` folder to the PATH:  
  - Right-click on "This PC" or "My Computer" and select "Properties".  
  - Click on "Advanced System Settings", then "Environment Variables".  
  - In the "System Variables" section, select the Path variable and click "Edit".  
  - Add the path to the Maven `bin` folder, something like: `C:\maven\apache-maven-3.8.1\bin` (check the correct path for your Maven).  
  - Click "OK" to save the changes.

Linux (Ubuntu/Debian):  
Install Maven with the following commands:  
`sudo apt update`  
`sudo apt install maven`

macOS (using Homebrew):  
If you use Homebrew, install Maven with the following command:  
`brew install maven`

Compilation and Execution  
Steps to Build and Run the Application  
1. Clone the repository to your local machine:  
`git clone https://github.com/HugoARSantos/java-challenge.git`

2. Navigate to the main project folder:  
`cd java-challenge`

3. Use Maven to compile the project:  
`mvn clean install`

4. Make sure Docker is running:

- Windows: Make sure Docker Desktop is running. Open Docker Desktop and wait until the interface shows that Docker is ready.  
- Linux: Check if the Docker service is running with the command `sudo systemctl status docker`. If it's not running, use `sudo systemctl start docker` to start the service.  
- macOS: Make sure Docker Desktop is running. Open Docker Desktop and wait until the interface shows that Docker is ready.

5. Create and start the application using Docker Compose:  
In the terminal, run the following command to build the application, generate the Docker image, and start the container:  
`docker-compose up --build -d`

6. The application will run on port 8080.

How to Use the API  
You can interact with the API using Postman, cURL, or by using a browser.

1. Using Postman  
With Postman, you can make HTTP requests to the API routes.

- Sum:  
  Method: POST  
  URL: `http://localhost:8080/api/calculator/sum`  
  Parameters:  
  - `a`: 2.5  
  - `b`: 3.5  

- Subtraction:  
  Method: POST  
  URL: `http://localhost:8080/api/calculator/subtract`  
  Parameters:  
  - `a`: 5.5  
  - `b`: 3.0  

- Multiplication:  
  Method: POST  
  URL: `http://localhost:8080/api/calculator/multiply`  
  Parameters:  
  - `a`: 5.5  
  - `b`: 3.0  

- Division:  
  Method: POST  
  URL: `http://localhost:8080/api/calculator/divide`  
  Parameters:  
  - `a`: 5.5  
  - `b`: 2.0  

- View last operation details:  
  Method: GET  
  URL: `http://localhost:8080/api/calculator/result`

2. Using cURL (from the command line)  
You can also test the routes using cURL directly from the command line.

- Sum:  
  `curl -X POST "http://localhost:8080/api/calculator/sum?a=2.5&b=3.5"`

- Subtraction:  
  `curl -X POST "http://localhost:8080/api/calculator/subtract?a=5.5&b=3.0"`

- Multiplication:  
  `curl -X POST "http://localhost:8080/api/calculator/multiply?a=5.5&b=3.0"`

- Division:  
  `curl -X POST "http://localhost:8080/api/calculator/divide?a=5.5&b=2.0"`

- View last operation details:  
  `curl -X GET "http://localhost:8080/api/calculator/result"`

For example, if you use the sum above, it will return:  
`Result: {"operation": "sum", "a": 2.5, "b": 3.5, "result": 6.0}`