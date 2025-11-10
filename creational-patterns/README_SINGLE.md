
# Singleton Design Pattern in Spring Boot

## What is Singleton Pattern?

The **Singleton Pattern** ensures a class has only **one instance** throughout the application and provides a global point of access to it.

### Structure

1️⃣ `private static` variable → holds the single instance  
2️⃣ `private` constructor → prevents direct instantiation  
3️⃣ `public static` method → returns the same instance globally

Real world analogy: 

1. A **president/pm** of a country — there is only one president at a time, and everyone refers to that single instance.
2. The Sun is another example — there is only one sun that provides light and energy to the entire planet.
3. Remote controls for TVs are also singletons — you have one remote control that manages the TV.

It’s often used for:
- Logging
- Database connections
- Configuration management
- Caching
- Thread pools

---

## Types of Singleton Implementations in Java

| # | Type                                            | Thread Safe? | Lazy Loading? | Description                                  |
| - | ----------------------------------------------- | ------------ | ------------- | -------------------------------------------- |
| 1 | **Eager Initialization**                        | ✅            | ❌             | Instance created at class load time          |
| 2 | **Lazy Initialization**                         | ❌            | ✅             | Instance created only when needed            |
| 3 | **Thread Safe Singleton (Synchronized Method)** | ✅            | ✅             | Safe for multithreaded use                   |
| 4 | **Double-Checked Locking**                      | ✅            | ✅             | Efficient thread-safe singleton              |
| 5 | **Bill Pugh (Inner Static Helper Class)**       | ✅            | ✅             | Best lazy-loaded and thread-safe             |
| 6 | **Enum Singleton**                              | ✅            | ✅             | Simplest, reflection- and serialization-safe |

---

## Quick Summary

| Feature                    | Eager               | Lazy   | Thread-Safe   | Best Practice |
|----------------------------|---------------------|--------|---------------| -------------- |
| **Performance**            | High (pre-created)  | Medium | High          | ✅ Bill Pugh |
| **Memory Efficient**       | ❌                   | ✅      | ✅             | ✅ Bill Pugh |
| **Serialization Safe**     | ❌                   | ❌      | ✅ Enum        | ✅ Enum |
| **Ease of Implementation** | ✅                   | ✅      | ⚠️ Moderate   | ✅ Enum |

---

## Recommended Use
- Use **Eager Initialization** only when the instance creation is cheap and you’re sure it will always be used.
- Use **Bill Pugh Singleton** for modern, efficient, thread-safe lazy initialization.
- Use **Enum Singleton** when you want guaranteed serialization safety and simplest implementation.

---

# 1️⃣ Eager Initialization

## Concept
In **Eager Initialization**, the instance of the Singleton class is created **when the class is loaded** — even if it is **never used** later.  
This ensures **thread safety**, but can **waste memory** if the instance is heavy and unused.

---

## Code Example
```java
public class SingletonEager {

    // Instance is created during class loading
    private static final SingletonEager instance = new SingletonEager();

    // Private constructor prevents instantiation
    private SingletonEager() {}

    // Global access point
    public static SingletonEager getInstance() {
        return instance;
    }
}
```
---

## Pros
- Simple to implement
- Thread-safe by default (since instance is created at class load time)

## Cons
- Instance created even if not used
- May waste memory for heavy objects

---

### Example Usage
```java
public class App {
    public static void main(String[] args) {
        SingletonEager obj1 = SingletonEager.getInstance();
        SingletonEager obj2 = SingletonEager.getInstance();

        System.out.println(obj1 == obj2); // true ✅ same instance
    }
}
```

---

> **Summary:**  
> *Eager Initialization* = “Create early, stay ready.”  
> Use only when instance creation is lightweight **and always required**.

---

# 2️⃣ Lazy Initialization

## Concept
In **Lazy Initialization**, the Singleton instance is **created only when it’s first requested** (on demand).  
This improves **memory efficiency**, but the **basic version is not thread-safe**.

---

## Code Example
```java
public class SingletonLazy {

    // Instance not created until needed
    private static SingletonLazy instance;

    private SingletonLazy() {}

    // Instance created when first requested
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
```
---

## Pros
- Instance created **only when required** (lazy loading)
- **Memory efficient**, especially for heavy objects

## Cons
- **Not thread-safe** — multiple threads could create separate instances simultaneously
- Requires synchronization for multithreaded environments
---

### Example Usage
```java
public class App {
    public static void main(String[] args) {
        SingletonLazy obj1 = SingletonLazy.getInstance();
        SingletonLazy obj2 = SingletonLazy.getInstance();

        System.out.println(obj1 == obj2); // true ✅ same instance
    }
}
```

---

> **Summary:**  
> *Lazy Initialization* = “Create only when needed.”  
> Best suited for **single-threaded** applications.  
> For multi-threading, use **Thread-Safe Singleton** or **Double-Checked Locking**.


# 3️⃣ Thread Safe Singleton (Synchronized Method)

---

## Concept
In this version, the `getInstance()` method is **synchronized** to ensure that **only one thread** can execute it at a time.  
This makes it **thread-safe**, but **synchronization adds performance cost** because every call to the method is locked — even after the instance is created.

---

## Code Example
```java
public class SingletonThreadSafe {

    private static SingletonThreadSafe instance;

    private SingletonThreadSafe() {}

    // Synchronized ensures thread-safety
    public static synchronized SingletonThreadSafe getInstance() {
        if (instance == null) {
            instance = new SingletonThreadSafe();
        }
        return instance;
    }
}
```

---

## Pros
- Thread-safe — only one instance even in multi-threaded environments
- Simple and easy to implement

## Cons
- **Slower performance** due to method-level synchronization
- Every call to `getInstance()` is synchronized — unnecessary after initialization

---

### Example Usage
```java
public class App {
    public static void main(String[] args) {
        SingletonThreadSafe obj1 = SingletonThreadSafe.getInstance();
        SingletonThreadSafe obj2 = SingletonThreadSafe.getInstance();

        System.out.println(obj1 == obj2); // true ✅ same instance
    }
}
```

---

> **Summary:**  
> *Thread Safe Singleton (Synchronized)* = “Safe but slow.”  
> Good for **multi-threaded environments**, but **not optimal** for high-performance use.  
> For better performance, use **Double-Checked Locking**.


### Bill Pugh Singleton

> The **Bill Pugh Singleton Pattern** uses a **static inner helper class** to hold the Singleton instance.  
> This leverages the **Java ClassLoader mechanism** to ensure that the instance is created **only when required** (lazy initialization)  
> and guarantees **thread safety** without using synchronized blocks.

---

### How it Works
- The **inner static class (Helper)** is not loaded until `getInstance()` is called.
- When it loads, JVM ensures the **thread-safe** creation of the instance.
- Thus, the Singleton is **lazy-loaded**, **thread-safe**, and **efficient**.

---

### Example — Bill Pugh Singleton
```java
public class ConfigManager {

    private ConfigManager() {
        System.out.println("ConfigManager initialized");
    }

    private static class Helper {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance() {
        return Helper.INSTANCE;
    }
}
```
---

### Pros
- Lazy initialization (created only when needed)
- Thread-safe (no need for synchronization)
- No performance overhead
- Clean and efficient

### Cons
- Vulnerable to **reflection** (can create another instance)
- Slightly more boilerplate code compared to Enum

### When to Use
- When you need a **lazy-loaded**, **thread-safe** Singleton
- Ideal for **ConfigManager**, **Cache**, or **ConnectionManager**


# Reflection Risk in Singleton

**Reflection** is a Java feature that allows you to:
- Inspect classes, methods, and fields **at runtime**, and
- Access or modify them — even if they are **private**.

> It’s part of the `java.lang.reflect` package and is used in frameworks like Spring and Hibernate.

### Problem with Singleton
Reflection can **bypass private constructors** and create **multiple instances** of a Singleton class, breaking the Singleton pattern.
### Example of Reflection Breaking Singleton
```java
import java.lang.reflect.Constructor;
public class ReflectionSingletonBreak {
    public static void main(String[] args) throws Exception {
        SingletonLazy instance1 = SingletonLazy.getInstance();

        // Using reflection to access private constructor
        Constructor<SingletonLazy> constructor = SingletonLazy.class.getDeclaredConstructor();
        constructor.setAccessible(true); // Bypass private access
        SingletonLazy instance2 = constructor.newInstance(); // Create new instance

        System.out.println(instance1 == instance2); // false ❌ different instances
    }
}
```
---

### How to Prevent Reflection Attacks
1. **Throw Exception in Constructor**: Modify the constructor to throw an exception if an instance already exists.
```java
private SingletonLazy() {
    if (instance != null) {
        throw new RuntimeException("Use getInstance() to create");
    }
}
```
2. **Use Enum Singleton**: Enums are inherently safe against reflection attacks.


### Enum Singleton
> The **Enum Singleton Pattern** uses a Java `enum` to implement the Singleton design pattern.
> This approach is **simple**, **thread-safe**, and **provides built-in serialization** support, making it immune to reflection attacks.
---

### How it Works
- Enum constants are instantiated **once** by the JVM.
- Even reflection or deserialization cannot create new instances.
- The Enum instance is available globally and created eagerly.

---

```java
public enum DatabaseConnection {
    INSTANCE;

    public void connect() {
        System.out.println("Connected to database...");
    }
}
```
---

### Pros
- Simplest Singleton implementation
- **Thread-safe**, **Serialization-safe**, **Reflection-proof**
- Very concise — only a few lines of code

### Cons
- **No lazy loading** (instance created at startup)
- **Cannot extend another class** (enums extend `java.lang.Enum`)
- Not flexible for complex initialization logic

### When to Use
- When you want a **simple, safe, and concise** Singleton
- Ideal for **Logger**, **DatabaseConnection**, or **AppSettings**

---

**Best Choice:**
> Use **Bill Pugh** or **Enum Singleton** for thread-safety, lazy loading, and protection against reflection and serialization attacks.

---


## Quick Comparison

| Feature | Bill Pugh | Enum |
|----------|-----------|------|
| Lazy Loading | ✅ Yes | ❌ No |
| Thread Safe | ✅ Yes | ✅ Yes |
| Reflection Safe | ❌ No | ✅ Yes |
| Serialization Safe | ❌ No | ✅ Yes |
| Code Simplicity |  Moderate | Very simple |
| Best Use Case | Config / Cache | Logger / DB Connection |

---

## Real-World Example: Database Connection Pool Manager

Imagine a **Banking Application** where multiple services need database connections:

### ❌ Without Singleton (Multiple Instances)

```
┌─────────────────────────────────────────────────────────────┐
│                    Banking Application                      │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────────────┐         ┌──────────────────┐          │
│  │ AccountService   │         │TransactionService│          │
│  │                  │         │                  │          │
│  │  [Connection     │         │  [Connection     │          │
│  │   Pool Manager]  │         │   Pool Manager]  │          │
│  │                  │         │                  │          │
│  │  • 10 connections│         │  • 10 connections│          │
│  └──────────────────┘         └──────────────────┘          │
│                                                             │
│  ┌──────────────────┐         ┌──────────────────┐          │
│  │  LoanService     │         │  ReportService   │          │
│  │                  │         │                  │          │
│  │  [Connection     │         │  [Connection     │          │
│  │   Pool Manager]  │         │   Pool Manager]  │          │
│  │                  │         │                  │          │
│  │  • 10 connections│         │  • 10 connections│          │
│  └──────────────────┘         └──────────────────┘          │
│                                                             │
│  Total: 40 connections created! ❌ Memory waste!            │
└─────────────────────────────────────────────────────────────┘
```

**Problems:**
- 40 connections created (instead of 10)
- High memory usage
- Resource waste

---

### With Singleton (One Instance)

```
┌─────────────────────────────────────────────────────────────┐
│                    Banking Application                      │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────────┐         ┌──────────────────┐          │
│  │ AccountService   │────────▶│                  │          │
│  └──────────────────┘         │                  │          │
│                                │   Connection     │          │
│  ┌──────────────────┐         │   Pool Manager   │          │
│  │TransactionService│────────▶│   (Singleton)    │          │
│  └──────────────────┘         │                  │          │
│                                │  • 10 connections│          │
│  ┌──────────────────┐         │    (shared)      │          │
│  │  LoanService     │────────▶│                  │          │
│  └──────────────────┘         │                  │          │
│                                │                  │          │
│  ┌──────────────────┐         │                  │          │
│  │  ReportService   │────────▶│                  │          │
│  └──────────────────┘         └──────────────────┘          │
│                                                               │
│  Total: 10 connections only! ✅ Efficient!                  │
└─────────────────────────────────────────────────────────────┘
```

**Benefits:**
- Only 10 connections (efficient resource usage)
- All services share the same pool
- Consistent state

---

## Code Implementation

### ✅ With Singleton (Default in Spring Boot)

```java
// Singleton Bean - Only ONE instance created
@Component
public class DatabaseConnectionPoolManager {
    
    private List<Connection> connectionPool = new ArrayList<>();
    
    public DatabaseConnectionPoolManager() {
        System.out.println("🔵 Creating Connection Pool Manager...");
        initializePool(10);
    }
    
    private void initializePool(int size) {
        System.out.println("✅ Initializing " + size + " connections");
        // Create connection pool
    }
    
    public Connection getConnection() {
        return connectionPool.isEmpty() ? null : connectionPool.get(0);
    }
}

@Service
public class AccountService {
    @Autowired
    private DatabaseConnectionPoolManager poolManager; // Same instance
    
    public void transfer() {
        Connection conn = poolManager.getConnection();
        System.out.println("💰 Transfer using shared pool");
    }
}

@Service
public class TransactionService {
    @Autowired
    private DatabaseConnectionPoolManager poolManager; // Same instance
    
    public void getHistory() {
        Connection conn = poolManager.getConnection();
        System.out.println("📊 Fetching history using shared pool");
    }
}
```

**Output:**
```
🔵 Creating Connection Pool Manager...
✅ Initializing 10 connections
💰 Transfer using shared pool
📊 Fetching history using shared pool
```

✅ **Only ONE instance** created and shared!

---

## Key Takeaway

**Use Singleton (default) for:**
- ✅ Database connection pools
- ✅ Configuration objects
- ✅ Services and repositories
- ✅ Thread pools
- ✅ Caching managers

# Spring Boot - Gang of Four (GoF) Creational Design Patterns

| GoF Pattern | Description | Spring Boot Annotation(s) | How It Applies |
|-------------|-------------|---------------------------|----------------|
| **Singleton** | Ensure one instance per application. | `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController` | All beans are singleton by default in Spring's ApplicationContext. |
| **Factory Method** | Delegate object creation to a factory method. | `@Bean`, `@Configuration` | Each `@Bean` method defines how to instantiate and return a bean (acts as a factory method). |
| **Abstract Factory** | Create families of related objects. | `@Configuration` + multiple `@Bean` methods | A configuration class acts as an abstract factory for related beans (e.g., multiple data sources, environments). |
| **Builder** | Build complex objects step by step. | `@Builder` (from Lombok) | Used for constructing complex objects fluently (e.g., DTOs, response entities). |
| **Prototype** | Create new instance each time requested. | `@Scope("prototype")` | Defines bean scope as prototype — a new instance is created every time it's requested. |


## Summary
| Term | Meaning |
|------|----------|
| **Singleton Pattern** | Ensures a class has only one instance and provides a global point of access to it. |
| **Default Scope in Spring** | All Spring beans are singleton by default. |
| **Use Cases** | Database connection pools, configuration objects, services, repositories. |
| **Prototype Scope** | Creates a new instance every time the bean is requested (`@Scope("prototype")`). |
