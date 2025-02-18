# Camunda 8 CI/CD and Tests

This PoC demonstrates how to integrate Web Modeler with a GitHub repository, triggering a CI/CD pipeline to run BPMN tests and deploy diagrams to your cluster.

## 🚀 Getting Started

### 1. Create a Process Application in Web Modeler

- In your Web Modeler project folder, create a new **Process Application**.

### 2. Set Up a GitHub App

- Follow the instructions in the official Camunda documentation to create a GitHub App:  
  [Git Sync - Camunda Documentation](https://docs.camunda.io/docs/next/components/modeler/web-modeler/git-sync/)

### 3. Sync Your Process Application with GitHub

- Use the same documentation above to synchronize your **Process Application** with your GitHub repository.

#### 🔹 Connecting Your Git Repository

![Button to connect your repository](.tutorial/connect to repository.png)

#### 🔹 Example: Git Sync Setup

![GitSync Setup](.tutorial/sync button setup.png)

#### 🔹 After Git Sync Setup

![GitSync done](.tutorial/github synced.png)

### 4. Sync Your Diagrams

- Click the **Git Sync** button to create a new version of your diagrams.

#### 🔹 Git Sync Button Pressed

![GitSync open](.tutorial/pop up sync button pressed.png)

#### 🔹 After Sync

![After sync](.tutorial/after sync.png)

### 5. Check Your Pipeline

- Once synced, your CI/CD pipeline will run tests and deploy your diagrams to the cluster.

![Pipeline running](.tutorial/pipeline running.png)

### 6. Configure Camunda Cluster Credentials

- Don't forget to add your **Camunda cluster credentials** in:

  ```yaml
  src/main/resources/application.yaml

### 7. Process and Test Locations

- The example BPMN process used in this PoC is located at:
```
  src/main/resources/processes/process-example.bpmn
```
- The test cases can be found in:
```
src/test/java/com/example/camunda
```

## 🧪 Tests

- ### 🔹 Zeebe Process Test (ZPT)

This PoC uses Zeebe Process Test (ZPT), a stable test library for Camunda 8.6.

- [Official Documentation](https://docs.camunda.io/docs/apis-tools/java-client/zeebe-process-test/#zeebe-spring-sdk-integration)
- [Example Test Cases](https://github.com/camunda-community-hub/camunda-8-examples/blob/main/twitter-review-java-springboot/src/test/java/org/camunda/community/examples/twitter/TestTwitterProcess.java)

- ### 🔹 Camunda Process Test (CPT) (For Camunda 8.7+)

Camunda 8.7 introduces Camunda Process Test (CPT), a new testing library based on JUnit 5 and Testcontainers.

- [Getting Started with CPT](https://docs.camunda.io/docs/apis-tools/testing/getting-started/)
- [Example Test Cases](https://github.com/camunda/camunda/tree/main/testing/camunda-process-test-example)

## ✅ Final Notes

With this setup, your BPMN processes are automatically tested and deployed upon synchronization with GitHub. 
Make sure to keep your tests updated to maintain the reliability of your workflows. 🚀



