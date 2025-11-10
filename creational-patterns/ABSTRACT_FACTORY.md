# Abstract Factory Design Pattern — Java & Spring Boot

---

## What is Abstract Factory Design Pattern?

> The **Abstract Factory Pattern** provides an interface for creating **families of related or dependent objects** without specifying their concrete classes.

It’s like a **factory of factories** — you don’t just create objects; you create entire groups of related objects through a common interface.

---

## Why Do We Need This?

### Problem Before This Pattern:
Before using the Abstract Factory pattern:
- Developers **hard-coded object creation** using `new`.
- When requirements changed (like switching from **Windows UI** to **Mac UI**), you had to modify **many places** in code.
- It became difficult to **maintain consistency** between related objects (e.g., a Windows button and Mac text field shouldn’t mix).
- The code **violated the Open-Closed Principle** — every time a new product variant was added, core logic changed.

👉 **In short:** It caused **tight coupling**, poor scalability, and duplication.

---

## Solution — Abstract Factory

Abstract Factory solves this by:
- Encapsulating the object creation logic into factories.
- Each factory knows how to create a **set of related products**.
- The client code just uses the factory — it doesn’t care about concrete classes.

---

## Real World Example — Cross-Platform UI Components

Imagine you are building a UI library that supports both **Windows** and **Mac** themes.

You need buttons and text fields for both, but you don’t want to change your code each time the OS changes.

So you create **factories** to handle this.

---

## Class Diagram (Simplified)

```
              ┌───────────────────┐
              │  GUIFactory       │  <─── Abstract Factory
              ├───────────────────┤
              │ + createButton()  │
              │ + createTextbox() │
              └───────────────────┘
                       ▲
        ┌──────────────┴──────────────┐
        │                             │
┌───────────────────┐        ┌───────────────────┐
│  WinFactory       │        │  MacFactory       │
├───────────────────┤        ├───────────────────┤
│ + createButton()  │        │ + createButton()  │
│ + createTextbox() │        │ + createTextbox() │
└───────────────────┘        └───────────────────┘
```

---

## Example Code

### With Abstract Factory Pattern

```java
// 1️⃣ Product Interfaces
interface Button {
    void paint();
}

interface TextBox {
    void render();
}

// 2️⃣ Concrete Products
class WinButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class MacButton implements Button {
    public void paint() {
        System.out.println("Rendering Mac Button");
    }
}

class WinTextBox implements TextBox {
    public void render() {
        System.out.println("Rendering Windows TextBox");
    }
}

class MacTextBox implements TextBox {
    public void render() {
        System.out.println("Rendering Mac TextBox");
    }
}

// 3️⃣ Abstract Factory
interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}

// 4️⃣ Concrete Factories
class WinFactory implements GUIFactory {
    public Button createButton() { return new WinButton(); }
    public TextBox createTextBox() { return new WinTextBox(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public TextBox createTextBox() { return new MacTextBox(); }
}

// 5️⃣ Client Code
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WinFactory(); // or new MacFactory()
        Button btn = factory.createButton();
        TextBox txt = factory.createTextBox();

        btn.paint();
        txt.render();
    }
}
```

Output (for `WinFactory`):
```
Rendering Windows Button
Rendering Windows TextBox
```

---

## Without Abstract Factory Pattern

```java
class Button {
    void paint(String os) {
        if (os.equals("Windows")) {
            System.out.println("Rendering Windows Button");
        } else if (os.equals("Mac")) {
            System.out.println("Rendering Mac Button");
        }
    }
}

class TextBox {
    void render(String os) {
        if (os.equals("Windows")) {
            System.out.println("Rendering Windows TextBox");
        } else if (os.equals("Mac")) {
            System.out.println("Rendering Mac TextBox");
        }
    }
}

public class WithoutFactoryDemo {
    public static void main(String[] args) {
        String os = "Mac";
        Button btn = new Button();
        TextBox txt = new TextBox();

        btn.paint(os);
        txt.render(os);
    }
}
```
 
Problems:
- Code is **full of if-else checks**.
- Every new platform (Linux, Android, etc.) adds **more conditions**.
- **No loose coupling** — changes ripple through the codebase.

---

## Comparison

| Aspect | Without Abstract Factory | With Abstract Factory |
|--------|--------------------------|------------------------|
| Object creation | Hardcoded using `new` | Delegated to factory |
| Flexibility | Low | High |
| Coupling | Tight | Loose |
| Maintainability | Poor | Easy to extend |
| Scalability | Difficult | Supports multiple product families |

---

## Advantages

1. **Encapsulates object creation** — No `new` scattered in the code.
2. **Ensures product consistency** — You get the right combination of related objects.
3. **Promotes loose coupling** — Client depends only on abstract interfaces.
4. **Supports scalability** — Easy to add new families (e.g., Linux UI).

---

## Disadvantages

1. **Complex structure** — More classes and interfaces.
2. **Difficult to add new product types** — You need to modify all factories.
3. **Overhead for small systems** — May feel overkill for simple object creation.

---

## Real World Examples in Spring Boot

| Area | Example |
|-------|----------|
| **Data Access Layer** | Different `DataSource` factories for MySQL, Oracle, PostgreSQL |
| **Bean Creation** | `@Bean` and `@Configuration` act like factories |
| **Cloud Provider Configs** | AWS vs Azure factories for service objects |

---

## Summary

| Term | Meaning |
|------|----------|
| **Abstract Factory** | Factory of related factories |
| **Goal** | Create related objects without specifying their exact classes |
| **Used When** | System should support multiple object families (themes, OS, DBs) |
| **Key Principle** | Open-Closed & Dependency Inversion |

---

# Factory Method vs Abstract Factory — Quick Comparison

---

## Overview

Both are **creational design patterns**, used to handle **object creation** —  
but they differ in **scope**, **complexity**, and **what they create**.

---

## Difference Table

| Feature | **Factory Method** | **Abstract Factory** |
|----------|----------------------|-------------------------|
| **Purpose** | Creates **one product** at a time | Creates **families of related products** |
| **Focus** | Defines a method to create an object; subclasses decide which class to instantiate | Provides an interface for creating *groups* of related objects without specifying concrete classes |
| **Level of Abstraction** | Lower — focuses on a single object | Higher — focuses on related object sets |
| **Structure** | One factory → one product | One factory → multiple related products |
| **Complexity** | Simple and easier to implement | More complex and layered |
| **Design Base** | Relies on **inheritance** (subclasses override factory method) | Relies on **composition** (factory interface + multiple concrete factories) |
| **When to Use** | When you need to decide which *one* object to create | When you need to create *multiple related objects* together |
| **Spring Equivalent** | `@Configuration` + `@Bean` (single object creation) | Multiple `@Configuration` classes per environment/family (e.g., `@Profile`) |
| **Example (Java)** | `NotificationFactory` → creates `EmailNotification` or `SMSNotification` | `ComputerFactory` → creates both `Laptop` and `Mouse` families |
| **Real-World Analogy** | ☕ *Coffee Machine* — you choose one type (Espresso, Latte, Cappuccino) |  *Restaurant Franchise* — each branch (McDonald’s, KFC) makes a whole meal combo (burger + fries + drink) |

---

## 🌍 Real-World Analogies — Factory Method vs Abstract Factory

| # | Scenario | **Factory Method** | **Abstract Factory** |
|---|-----------|----------------------|-------------------------|
| **1** | **Coffee Shop** | One coffee machine prepares a single drink type at a time — Espresso, Latte, or Cappuccino. | A global coffee chain (like Starbucks) defines full menu sets — coffee + mug + snack + packaging — all related and styled together. |
| **2** | **Car Manufacturing** | A car plant produces one model at a time — Sedan, SUV, or Hatchback. | A brand (like Tesla or BMW) produces a family — Car + Engine + Battery + Charging Station — all designed to work together. |
| **3** | **Smart Home System** | A factory manufactures one smart device — a bulb, thermostat, or camera. | A brand ecosystem (like Philips or Xiaomi) creates a whole product family — smart bulb + hub + app + voice integration. |
| **4** | **E-commerce Platform (Flipkart)** | A single factory (category) handles one product type — e.g., Laptop, Mobile, or Book. | Flipkart as a whole platform defines families — Electronics (Laptop + Charger + Warranty), Fashion (Shirt + Shoes + Accessories). |
| **5** | **Gaming Consoles** | Factory makes a single controller (Xbox or PlayStation controller). | Each console brand offers a complete set — Console + Controller + Subscription + Accessories — all working as a unit. |

---

### Quick Recap

> **Factory Method** → One product at a time (single creation process)  
> **Abstract Factory** → Group or family of related products (complex creation ecosystem)


### In Short:
> The Abstract Factory Pattern builds a **bridge between client and concrete classes**, ensuring your system is **flexible, scalable, and easy to maintain** — no matter how many product families you add in the future.

---

