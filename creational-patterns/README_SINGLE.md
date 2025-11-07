
# Singleton Design Pattern in Spring Boot

## What is Singleton Pattern?

The **Singleton Pattern** ensures a class has only **one instance** throughout the application and provides a global point of access to it.

---

## Real-World Example: Database Connection Pool Manager

Imagine a **Banking Application** where multiple services need database connections:

### ❌ Without Singleton (Multiple Instances)

```
┌─────────────────────────────────────────────────────────────┐
│                    Banking Application                       │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────────┐         ┌──────────────────┐          │
│  │ AccountService   │         │TransactionService│          │
│  │                  │         │                  │          │
│  │  [Connection     │         │  [Connection     │          │
│  │   Pool Manager]  │         │   Pool Manager]  │          │
│  │                  │         │                  │          │
│  │  • 10 connections│         │  • 10 connections│          │
│  └──────────────────┘         └──────────────────┘          │
│                                                               │
│  ┌──────────────────┐         ┌──────────────────┐          │
│  │  LoanService     │         │  ReportService   │          │
│  │                  │         │                  │          │
│  │  [Connection     │         │  [Connection     │          │
│  │   Pool Manager]  │         │   Pool Manager]  │          │
│  │                  │         │                  │          │
│  │  • 10 connections│         │  • 10 connections│          │
│  └──────────────────┘         └──────────────────┘          │
│                                                               │
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
│                    Banking Application                       │
├─────────────────────────────────────────────────────────────┤
│                                                               │
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

### Without Singleton (Using @Scope("prototype"))

```java
// Multiple instances created
@Component
@Scope("prototype")  // Creates NEW instance every time
public class DatabaseConnectionPoolManager {
    
    private List<Connection> connectionPool = new ArrayList<>();
    
    public DatabaseConnectionPoolManager() {
        System.out.println("🔴 Creating NEW Connection Pool Manager...");
        initializePool(10);
    }
    
    private void initializePool(int size) {
        System.out.println("❌ Initializing " + size + " connections");
    }
    
    public Connection getConnection() {
        return connectionPool.isEmpty() ? null : connectionPool.get(0);
    }
}

@Service
public class AccountService {
    @Autowired
    private DatabaseConnectionPoolManager poolManager; // New instance #1
    
    public void transfer() {
        Connection conn = poolManager.getConnection();
        System.out.println("💰 Transfer");
    }
}

@Service
public class TransactionService {
    @Autowired
    private DatabaseConnectionPoolManager poolManager; // New instance #2
    
    public void getHistory() {
        Connection conn = poolManager.getConnection();
        System.out.println("📊 Fetching history");
    }
}
```

**Output:**
```
🔴 Creating NEW Connection Pool Manager...
❌ Initializing 10 connections
🔴 Creating NEW Connection Pool Manager...
❌ Initializing 10 connections
💰 Transfer
📊 Fetching history
```

**TWO instances** created = 20 connections wasted!

---

## Bean Scopes in Spring Boot

| Scope | Creates New Instance? | Use Case |
|-------|----------------------|----------|
| `singleton` (default) | ❌ No - One instance only | Services, Repositories, Configuration |
| `@Scope("prototype")` | ✅ Yes - Every time | Temporary objects, User-specific data |
| `@Scope("request")` | ✅ Yes - Per HTTP request | Web request data |
| `@Scope("session")` | ✅ Yes - Per HTTP session | User session data |

---

## Visual Comparison

```
Singleton Pattern:
═════════════════════════════════════════
Request 1 ──┐
            ├──▶ [Single Instance] ◀──┐
Request 2 ──┘                         ├── Request 3
                                      ┘
All requests share ONE instance


Prototype Pattern:
═════════════════════════════════════════
Request 1 ──▶ [Instance 1]

Request 2 ──▶ [Instance 2]

Request 3 ──▶ [Instance 3]

❌ Each request creates NEW instance
```

---

## Key Takeaway

**Use Singleton (default) for:**
- ✅ Database connection pools
- ✅ Configuration objects
- ✅ Services and repositories
- ✅ Thread pools
- ✅ Caching managers

**Use Prototype (`@Scope("prototype")`) for:**
- ✅ User-specific objects
- ✅ Temporary data holders
- ✅ Objects with mutable state per user

# Spring Boot - Gang of Four (GoF) Structural Design Patterns

| GoF Pattern | Description | Spring Boot Annotation(s) | How It Applies |
|-------------|-------------|---------------------------|----------------|
| **Singleton** | Ensure one instance per application. | `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController` | All beans are singleton by default in Spring's ApplicationContext. |
| **Factory Method** | Delegate object creation to a factory method. | `@Bean`, `@Configuration` | Each `@Bean` method defines how to instantiate and return a bean (acts as a factory method). |
| **Abstract Factory** | Create families of related objects. | `@Configuration` + multiple `@Bean` methods | A configuration class acts as an abstract factory for related beans (e.g., multiple data sources, environments). |
| **Builder** | Build complex objects step by step. | `@Builder` (from Lombok) | Used for constructing complex objects fluently (e.g., DTOs, response entities). |
| **Prototype** | Create new instance each time requested. | `@Scope("prototype")` | Defines bean scope as prototype — a new instance is created every time it's requested. |