---

# PART II

# Classes & Objects

---

# Chapter 2

# Class

> "Every object begins with a class."

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain what a class really is.
- Understand why Java introduced classes.
- Differentiate between a class and an object.
- Understand how a class is represented internally.
- Explain the compilation process from `.java` to `.class`.
- Describe the JVM's view of a class.
- Understand when and how classes are loaded.
- Explain the role of `java.lang.Class`.
- Answer interview questions related to Java classes confidently.

---

# 2.1 Why Do Classes Exist?

Before defining a class, let's ask a more important question:

> **Why does Java even need classes?**

Imagine you're building an Employee Management System.

Your company has:

- 5 employees today.
- 500 employees next year.
- 50,000 employees after expansion.

Would you write separate code for every employee?

```
Employee1

Employee2

Employee3

Employee4

...

Employee50000
```

Of course not.

That would be impossible to maintain.

Instead, all employees share common characteristics:

```
Name

Employee ID

Department

Salary
```

and common behaviors:

```
work()

takeLeave()

calculateSalary()
```

Rather than defining these repeatedly, Java allows us to define them **once**.

That single definition is called a **Class**.

From that class, we can create as many employees as needed.

```
              Employee Class

                     │

     ┌───────────────┼───────────────┐

     ▼               ▼               ▼

 Employee A     Employee B     Employee C
```

One definition.

Many objects.

This dramatically reduces duplication and improves maintainability.

---

## The Fundamental Problem Classes Solve

Without classes:

```
Data

+

Functions

+

More Data

+

More Functions

↓

Chaos
```

With classes:

```
Employee

↓

Data

+

Behavior

↓

One Logical Unit
```

A class groups everything related to an entity into one place.

This organization is the foundation of object-oriented software.

---

## Real-World Analogy

Consider a car manufacturing company.

Toyota designs a single blueprint for the Toyota Corolla.

Using that blueprint, the factory manufactures thousands of cars.

```
Blueprint

↓

Factory

↓

Car 1

Car 2

Car 3

...

Car 100000
```

The blueprint is **not** a car.

It only describes how cars should be built.

Similarly,

a Java class is **not** an object.

It describes how objects should be created.

This analogy is useful—but, as we'll see next, it is also incomplete.
