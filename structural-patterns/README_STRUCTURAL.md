# Structural Design Patterns in Java

---

## Definition

> **Structural Design Patterns** define how classes and objects are combined to form larger, flexible systems — while keeping them efficient and loosely coupled.

In simple terms  
They show **how to connect components like LEGO blocks** so your code stays reusable, extensible, and easy to maintain.

---

## Why Structural Patterns?

Before these patterns:
- Code was **tightly coupled** and **hard to modify**.
- Any small change broke multiple classes.
- Reusability was minimal.

After applying them:
- Classes interact through **interfaces and composition**.
- Systems become **modular**, **extensible**, and **easier to test**.
- You can plug in new features without breaking old ones.

---

## Subtypes of Structural Patterns

| # | Pattern       | Description                                                              | Real-World Analogy                 |
|---|---------------|--------------------------------------------------------------------------|------------------------------------|
| 1️⃣ | **Adapter**   | Converts one interface into another compatible one                       | Plug adapter converts 3-pin to 2-pin |
| 2️⃣ | **Bridge**    | Separates abstraction from implementation so both can vary independently | Remote control and TV              |
| 3️⃣ | **Composite** | Treats individual and group objects uniformly                            | Folders and Files                  |
| 4️⃣ | **Decorator** | Adds new behavior dynamically without modifying existing code            | Coffee with milk and sugar         |
| 5️⃣ | **Facade**    | Simplifies complex subsystem access via a single interface               | Hotel Reception Desk               |
| 6️⃣ | **Flyweight** | Shares common objects to reduce memory                                   | Font characters in a Word Processor |
| 7️⃣ | **Proxy**     | Acts as a gateway or substitute for another object                       | ATM ↔ Bank Server                |

---

## Advantages

| Advantage                                    |
|----------------------------------------------|
| Promotes **loose coupling** between components |
| Encourages **composition over inheritance**  |
| Simplifies **complex system interactions**   |
| Enhances **code reusability and flexibility** |
| Makes code **easier to maintain and extend** |

---

## Disadvantages

| Disadvantage                                            |
|---------------------------------------------------------|
| Adds **extra abstraction layers** (slightly more complex) |
| More **classes and objects** in the design              |
| Can be **harder to debug** due to multiple layers       |
| Slight performance overhead in some cases               |

---

## When to Use Structural Patterns

Use them when:
- You want to **connect incompatible systems**
- You need to **simplify complex logic**
- You want to **add behavior dynamically**
- You want to **reduce coupling** or **save memory**

Avoid when:
- The system is simple (extra abstraction isn’t needed)
- You can directly connect classes without wrappers

---

## Quick Java Examples

### Adapter Pattern Example

```java
interface MediaPlayer {
    void play(String fileType, String fileName);
}

class AdvancedMediaPlayer {
    void playMP4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
}

class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

    public void play(String fileType, String fileName) {
        if (fileType.equalsIgnoreCase("mp4"))
            advancedPlayer.playMP4(fileName);
        else
            System.out.println("Invalid format: " + fileType);
    }
}

public class AudioPlayer {
    private MediaAdapter adapter = new MediaAdapter();
    public void play(String fileType, String fileName) {
        adapter.play(fileType, fileName);
    }
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();
        player.play("mp4", "song.mp4");
    }
}
```

Output:
```
Playing MP4 file: song.mp4
```

---

### Facade Pattern Example

```java
class CPU { void start() { System.out.println("CPU starting..."); } }
class Memory { void load() { System.out.println("Memory loading..."); } }
class HardDrive { void read() { System.out.println("Reading data..."); } }

class ComputerFacade {
    private final CPU cpu = new CPU();
    private final Memory memory = new Memory();
    private final HardDrive hd = new HardDrive();

    void startComputer() {
        cpu.start();
        memory.load();
        hd.read();
        System.out.println("Computer started successfully!");
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        new ComputerFacade().startComputer();
    }
}
```

Output:
```
CPU starting...
Memory loading...
Reading data...
Computer started successfully!
```

---

## UML Overview (Conceptual)

```text
                 ┌────────────────────────┐
                 │   Structural Patterns   │
                 └────────────┬────────────┘
                              │
     ┌──────────────┬────────┬─────────────┬──────────────┐
     │ Adapter      │ Bridge │ Composite   │ Decorator    │
     │ Facade       │ Proxy  │ Flyweight   │              │
     └──────────────┴────────┴─────────────┴──────────────┘
```

---

## Summary Table

| # | Pattern | Core Purpose | Common Use Case |
|---|----------|---------------|----------------|
| 1️⃣ | Adapter | Make incompatible interfaces work together | Integrating old & new APIs |
| 2️⃣ | Bridge | Separate abstraction & implementation | GUI toolkits, device drivers |
| 3️⃣ | Composite | Treat groups & individuals the same | File system trees |
| 4️⃣ | Decorator | Add features dynamically | Java I/O Streams |
| 5️⃣ | Facade | Simplify complex systems | Spring Boot Starter APIs |
| 6️⃣ | Flyweight | Share common data to save memory | Game objects, fonts |
| 7️⃣ | Proxy | Control access to real object | Security, caching, lazy loading |

---

## Quick Analogy — “The LEGO Principle” 

Think of each pattern as a **Lego connector piece**.  
You can build complex systems by simply **connecting reusable blocks**,  
instead of rewriting or tightly binding everything together.

---
# Spring Boot - Gang of Four (GoF) Structural Design Patterns

This document maps common **Structural Design Patterns** from the Gang of Four to their implementations in **Spring Boot**.

## Structural Patterns Overview

| GoF Pattern | Description | Spring Boot Annotation(s) | How It Applies |
|-------------|-------------|---------------------------|----------------|
| **Adapter** | Convert one interface to another. | `@RequestBody`, `@ResponseBody`, `@ModelAttribute`, `@JsonSerialize`, `@JsonDeserialize` | Adapts between Java objects and external formats (JSON, XML, form data). |
| **Bridge** | Decouple abstraction from implementation. | `@Repository` (JPA abstraction) | JPA repositories bridge abstraction (interface) with concrete data access implementation. |
| **Composite** | Compose objects into tree structures. | (No direct annotation) — used in Spring context hierarchies (`ApplicationContext` nesting). | Useful in hierarchical contexts or configuration trees. |
| **Decorator** | Add behavior dynamically. | `@Component` + AOP annotations (`@Aspect`, `@Around`, `@Before`, `@After`) | Adds cross-cutting concerns dynamically to existing beans. |
| **Facade** | Provide a unified interface to a subsystem. | `@RestController`, `@Controller`, `@Service` | Acts as a simplified entry point to complex business logic or subsystems. |
| **Flyweight** | Reuse existing objects efficiently. | `@Cacheable`, `@EnableCaching` | Reduces memory use by caching and reusing frequently requested objects. |
| **Proxy** | Control access to another object. | `@Transactional`, `@Async`, `@Cacheable`, `@Secured`, `@PreAuthorize` | Spring wraps target objects in proxy classes to add behavior like transactions, async, caching, and security. |

## Key Takeaways

- **Spring Boot extensively uses structural patterns** to provide clean abstractions and powerful features.
- **AOP (Aspect-Oriented Programming)** is the foundation for many patterns like Decorator and Proxy.
- Understanding these patterns helps in designing maintainable and scalable Spring Boot applications.
