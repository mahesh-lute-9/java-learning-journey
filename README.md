# ☕ Java Learning Journey — From Basics to Advanced (with Internals)

![Java](https://img.shields.io/badge/Language-Java-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-In%20Progress-brightgreen)
![Last Commit](https://img.shields.io/github/last-commit/mahesh-lute-9/java-learning-journey)
![Repo Size](https://img.shields.io/github/repo-size/mahesh-lute-9/java-learning-journey)
![Stars](https://img.shields.io/github/stars/mahesh-lute-9/java-learning-journey?style=social)

> 📚 A personal, day-by-day log of my journey learning **Java — from the absolute basics to advanced concepts and JVM internals**. This repo is both my practice notebook and a reference guide for anyone walking the same path.

<!-- LAST_UPDATED_START -->
_Last updated: 2026-06-16 18:13 UTC · 6 day(s) logged_
<!-- LAST_UPDATED_END -->

---

## 📖 About This Repository

This repository documents my structured journey of learning Java — not just the **syntax**, but **how things work under the hood**. The goal is to go beyond "writing code that compiles" and build a deep understanding of:

- Core Java fundamentals and OOP principles
- The Collections Framework, Generics, and Exception handling
- Modern Java (Java 8+) features like Streams, Lambdas, and the Functional API
- Multithreading and Concurrency
- **JVM Internals** — memory management, class loading, garbage collection, and the JIT compiler
- Design patterns and best practices used in real-world Java applications

Every folder represents either daily practice, topic-wise assignments, or notes on internals — all written, tested, and refined by me as I learn.

---

## 🎯 Goals of This Journey

- ✅ Build a rock-solid foundation in Core Java
- ✅ Understand **why** Java behaves the way it does (internals, not just APIs)
- ✅ Practice consistently — code every day, however small
- ✅ Create a reference repo I (and others) can revisit anytime
- ✅ Move from "I can write Java" to "I understand Java"

---

## 🗺️ Learning Roadmap

| Phase | Topics | Status |
|-------|--------|--------|
| **1. Java Basics** | Syntax, variables, data types, operators, control flow, arrays, strings | 🟢 In Progress |
| **2. Object-Oriented Programming** | Classes & objects, constructors, inheritance, polymorphism, abstraction, encapsulation, interfaces vs abstract classes | ⚪ Upcoming |
| **3. Core APIs** | Collections Framework, Generics, Exception handling, String pool, I/O & NIO | ⚪ Upcoming |
| **4. Java 8+ Features** | Lambda expressions, Functional interfaces, Streams API, Optional, Method references, Date/Time API | ⚪ Upcoming |
| **5. Multithreading & Concurrency** | Threads, Runnable, Executor framework, synchronization, locks, concurrent collections | ⚪ Upcoming |
| **6. JVM Internals** | Class loading mechanism, bytecode basics, JVM memory model (Stack, Heap, Metaspace), Garbage Collection algorithms, JIT compiler | ⚪ Upcoming |
| **7. Advanced Java** | Reflection, annotations, serialization, design patterns, JDBC, networking basics | ⚪ Upcoming |
| **8. Tooling & Ecosystem** | Maven/Gradle basics, JUnit testing, Git workflow, debugging tools | ⚪ Upcoming |

**Legend:** 🟢 In Progress · ✅ Completed · ⚪ Upcoming

> This roadmap is updated manually as phases complete — it's a high-level map, while the Progress Log below tracks day-to-day detail automatically.

---

## 📁 Repository Structure
```
├── DailyCodes/          # Day-wise practice files — small programs, syntax drills, quick experiments
│   ├── Datatypes/
│   ├── JVM_0/
│   ├── Operators/
│   ├── TypeCasting/
│   ├── ControlStatements/
│   └── ...
│
├── Assignments/          # Topic-wise assignments and exercises to reinforce concepts
│
```
---

## 🚀 How to Run the Code

1. **Clone the repository**
   ```bash
   git clone https://github.com/mahesh-lute-9/java-learning-journey.git
   cd java-learning-journey
   ```

2. **Compile a Java file**
   ```bash
   javac DailyCodes/Day01_Basics/HelloWorld.java
   ```

3. **Run the compiled class**
   ```bash
   java -cp DailyCodes/Day01_Basics HelloWorld
   ```

> 💡 Recommended: Use **JDK 17 or later** and an IDE like IntelliJ IDEA, Eclipse, or VS Code (with the Java Extension Pack) for the best experience.

---

## 📊 Progress Log

_This table is auto-generated from `progress.yml` by a GitHub Actions workflow. Do not edit it directly — edit `progress.yml` instead._

<!-- PROGRESS_LOG_START -->
| Date | Day | Topic | Folder |
|------|-----|-------|--------|
| 2026-05-26 | 1 | Data Types - Practicing real-world datatype scenarios | [DailyCodes/Datatypes](DailyCodes/Datatypes) |
| 2026-05-26 | 2 | JVM Basics - How Java code runs under the hood | [DailyCodes/JVM_0](DailyCodes/JVM_0) |
| 2026-06-02 | 3 | Operators - Deep dive into types of operators | [DailyCodes/Operators](DailyCodes/Operators) |
| 2026-06-14 | 4 | Type Casting - Implicit & Explicit conversions | [DailyCodes/TypeCasting](DailyCodes/TypeCasting) |
| 2026-06-16 | 5 | Control Statements - if, if_else, if_else_ladder, switch, loops (for, while, do-while), continue and break keywords | [DailyCodes/ControlStatements](DailyCodes/ControlStatements) |
| 2026-06-17 | 6 | Input Output in Java using Scanner class | [DailyCodes/IO](DailyCodes/IO) |
<!-- PROGRESS_LOG_END -->

---

## 📝 Notes & Key Takeaways

_Auto-generated from the `notes` field of each entry in `progress.yml`._

<!-- NOTES_START -->
### 2026-05-26 — Data Types - Practicing real-world datatype scenarios
- Practiced various datatype scenarios and edge cases to build intuition for how Java handles them.

### 2026-05-26 — JVM Basics - How Java code runs under the hood
- Learned the basics of how the JVM works and added explanations alongside existing programs to connect theory with code.

### 2026-06-02 — Operators - Deep dive into types of operators
- Practiced operators with a focus on all the types of operators with programs

### 2026-06-14 — Type Casting - Implicit & Explicit conversions
- Practiced converting between primitive types - implicit/widening (e.g. int to long) happens automatically, while explicit/narrowing (e.g. double to int) requires a cast and can lose precision.

### 2026-06-16 — Control Statements - if, if_else, if_else_ladder, switch, loops (for, while, do-while), continue and break keywords
- Explored all Control statements in detail - how it works under the hood with Iterable/Iterator - and practiced several programs using it.

### 2026-06-17 — Input Output in Java using Scanner class
- Understand diff types of inputs and explored all the concept behind input output in Java.  Practiced codes using Scanner class
<!-- NOTES_END -->

---

## 🔗 References & Resources

_Auto-generated from the `resources` field of each entry in `progress.yml` — articles, docs, and videos used while learning each topic._

<!-- RESOURCES_START -->
_No references yet. Add a `resources` list to any entry in `progress.yml`._
<!-- RESOURCES_END -->

---

## 🛠️ Tools & Setup

- **Language:** Java (JDK 17+)
- **IDE:** IntelliJ IDEA / Eclipse / VS Code
- **Version Control:** Git & GitHub
- **Build Tool:** (To be added when Maven/Gradle is introduced)

---

## 🙌 Why Share This Publicly?

- To stay **accountable** and consistent
- To create a resource that might help **other learners** following a similar path
- To get feedback, suggestions, and corrections from the community — issues and PRs with suggestions are welcome!

---

## 🤝 Connect With Me

- GitHub: [@mahesh-lute-9](https://github.com/mahesh-lute-9)

If you're on a similar Java learning journey, feel free to ⭐ star this repo, follow along, or open a discussion!

---

## 📜 License

This repository is for educational purposes. Feel free to use the code/notes for your own learning — attribution is appreciated but not required.

---

<p align="center"><i>"First, solve the problem. Then, write the code." — Consistency over intensity, one day at a time. 🚀</i></p>
