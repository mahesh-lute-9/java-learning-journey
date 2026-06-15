# ☕ Java Learning Journey — From Basics to Advanced (with Internals)

![Java](https://img.shields.io/badge/Language-Java-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-In%20Progress-brightgreen)
![Last Commit](https://img.shields.io/github/last-commit/mahesh-lute-9/java-learning-journey)
![Repo Size](https://img.shields.io/github/repo-size/mahesh-lute-9/java-learning-journey)
![Stars](https://img.shields.io/github/stars/mahesh-lute-9/java-learning-journey?style=social)

> 📚 A personal, day-by-day log of my journey learning **Java — from the absolute basics to advanced concepts and JVM internals**. This repo is both my practice notebook and a reference guide for anyone walking the same path.

<!-- LAST_UPDATED_START -->
_Last updated: 2026-06-15 20:57 UTC · 1 day(s) logged_
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
java-learning-journey/
│
├── DailyCodes/          # Day-wise practice files — small programs, syntax drills, quick experiments
│   ├── Day01_Basics/
│   ├── Day02_Operators/
│   └── ...
│
├── Assignments/          # Topic-wise assignments and exercises to reinforce concepts
│   ├── OOP/
│   ├── Collections/
│   └── ...
│
├── Notes/                # (Planned) Concept notes, diagrams, and explanations of internals
│
├── Projects/             # (Planned) Small mini-projects applying multiple concepts together
│
├── progress.yml          # Structured log: drives the auto-generated sections below
├── scripts/
│   └── generate_readme.py
├── .github/workflows/
│   └── update-readme.yml
└── README.md
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
| 2026-06-16 | 1 | Java Basics - Variables, Data Types & Type Casting | [DailyCodes/Day01_Basics](DailyCodes/Day01_Basics) |
<!-- PROGRESS_LOG_END -->

---

## 📝 Notes & Key Takeaways

_Auto-generated from the `notes` field of each entry in `progress.yml`._

<!-- NOTES_START -->
### 2026-06-16 — Java Basics - Variables, Data Types & Type Casting
- Java has 8 primitive types; everything else is a reference type.
- Widening (int -> long) happens automatically; narrowing (long -> int) needs an explicit cast and can lose data.
- 'final' on a variable means the reference can't be reassigned, not that the object itself is immutable.
<!-- NOTES_END -->

---

## 🔗 References & Resources

_Auto-generated from the `resources` field of each entry in `progress.yml` — articles, docs, and videos used while learning each topic._

<!-- RESOURCES_START -->
### 2026-06-16 — Java Basics - Variables, Data Types & Type Casting
- [Oracle Docs - Primitive Data Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [Baeldung - Type Casting in Java](https://www.baeldung.com/java-type-casting)
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
