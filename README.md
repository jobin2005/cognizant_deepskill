# Cognizant Digital Nurture 5.0 - Deep Skilling 🚀

Welcome to the **Cognizant Deep Skilling** workspace! This repository contains hands-on projects and exercises aimed at building a robust foundation as a Full Stack Engineer.

## 📂 Repository Contents

This workspace is divided into key technological milestones, covering everything from Version Control and Backend APIs to advanced Frontend Architectures.

### 1. 🛠️ Git Foundations (`/GitDemoLab`)
- Core Git integration and version control fundamentals.
- Managing staging, commits, tracking branches, and remote repositories.

### 2. 🍃 Spring Core and Maven (`/Spring Core and Maven`)
- Backend architectures working with Java, Spring Boot, and Maven.
- Demonstrates Application Context, Beans, and basic Library Management controller setups.

### 3. ng Student Course Portal (`/Angular_HandsOn/Jobin/student-course-portal`)
A comprehensive, end-to-end modern web application built progressively across **10 Hands-On Exercises** using **Angular (v20)**.

#### ✨ Features Highlight:
- **Standalone Components**: Clean, module-free modern Angular architecture.
- **Dynamic Data Binding**: Utilizing Property, Event, and Two-Way bindings.
- **Custom Directives & Pipes**: Featuring dynamic DOM highlighting and custom structural transformations.
- **Robust Forms**:
  - *Template-Driven Forms* using HTML-bound validations.
  - *Reactive Forms* using `FormBuilder`, custom synchronous validators, asynchronous mock network validators, and dynamic `FormArray` inputs.
- **Services & Dependency Injection**: Singleton data stores alongside component-scoped providers.
- **Advanced Routing**: Route Parameters (`/courses/:id`), Lazy-Loaded Modules, and functional Security Guards (`CanActivate` / `CanDeactivate`).
- **HTTP Client & RxJS**: Connecting to a `json-server` mock backend utilizing core RxJS operators (`map`, `catchError`, `tap`) and fully stacked interceptors (Auth, Setup, Loading).
- **Enterprise State Management**: Wired with `@ngrx/store` incorporating Actions, Reducers, Effects, and Redux DevTools integration.
- **Unit Testing Verification**: Verified specs written with Jasmine utilizing `TestBed` and `provideMockStore`.

## 🚀 Running the Angular Project

If you want to view the Student Course Portal locally:

1. Navigate to the project directory:
   ```bash
   cd Angular_HandsOn/Jobin/student-course-portal
   ```
2. Start the Mock Database API:
   ```bash
   npx json-server --watch db.json --port 3000
   ```
3. Start the Angular Dev Server:
   ```bash
   npx ng serve
   ```
4. Open your browser and navigate to `http://localhost:4200` to interact with the application!

---

*This repository represents the full journey mapping modern Full Stack developmental capabilities achieved throughout the skilling program!*
