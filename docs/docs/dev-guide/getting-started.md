---
sidebar_position: 1
title: Getting Started
---

# Developer Guide: Getting Started

Welcome to the Odoo-Clone project! This guide will walk you through setting up your local development environment from checking out the code to running the backend, frontend, and documentation servers.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
* **Java 21 (JDK)**: Required for the Spring Boot backend.
* **Node.js (v18+) & npm**: Required for the React frontend and Docusaurus documentation.
* **Docker Desktop** (or equivalent): Required for running the local PostgreSQL database and Testcontainers during backend tests.
* **Maven (v3.9+)**: Required for building the Java backend.

---

## Step 1: Clone the Repository

Clone the project to your local machine and navigate into the root directory:

```bash
git clone https://github.com/j-r-p/odoo-clone.git
cd odoo-clone
```

---

## Step 2: Database Setup (PostgreSQL)

The backend requires a PostgreSQL database. The easiest way to run this locally without polluting your system is via Docker.

Run the following command to spin up a detached PostgreSQL container configured exactly as the backend expects:

```bash
docker run --name odoo-postgres \
  -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=odoodb \
  -d postgres:16-alpine
```

> **Note:** When you run backend tests (`mvn test`), Spring Boot will automatically use Testcontainers to spin up a separate, disposable database just for testing.

---

## Step 3: Start the Spring Boot Backend

Open a terminal, navigate to the backend directory, and start the Spring Boot application. Flyway will automatically run and execute the necessary database migrations on startup.

```bash
cd backend
mvn clean install -DskipTests
mvn spring-boot:run
```

The backend API will start on `http://localhost:8080`. You can view the auto-generated Swagger API documentation at `http://localhost:8080/swagger-ui.html`.

---

## Step 4: Start the React Frontend

Open a new terminal window, navigate to the frontend directory, install the Node dependencies, and start the Vite development server.

```bash
cd frontend
npm install
npm run dev
```

The frontend application will start on `http://localhost:5173`. Vite is pre-configured to proxy `/api` requests directly to your running Spring Boot backend.

---

## Step 5: Start the Documentation Server (Docusaurus)

We use Docusaurus to maintain our developer and user guides. To view or edit the documentation locally, open a new terminal window, navigate to the docs directory, and start the server.

```bash
cd docs
npm install
npm start
```

The documentation website will be available at `http://localhost:3000`.

---

## Summary of Local Services

Once everything is running, here is where you can access your local services:

| Service                        | URL                                         |
|-------------------------------|---------------------------------------------|
| React Frontend                | http://localhost:5173                       |
| Backend API (Spring Boot)     | http://localhost:8080                       |
| Swagger API Docs              | http://localhost:8080/swagger-ui.html       |
| Documentation (Docusaurus)    | http://localhost:3000                       |
| PostgreSQL Database           | localhost:5432 (via pgAdmin or DBeaver)     |