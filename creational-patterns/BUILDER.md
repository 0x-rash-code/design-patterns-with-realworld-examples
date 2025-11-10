# Builder Design Pattern

---

## What is the Builder Pattern?

> The **Builder Pattern** is a **creational design pattern** that lets you construct complex objects **step-by-step**.  
> It helps when an object has **many optional parameters** or **combinations of configurations**.

You **don’t pass everything to a big constructor** — instead, you **build** the object gradually and clearly.

---

### Analogy (Simple Way)

Imagine you’re building a **Pizza** 🍕:

- You can choose size, crust, toppings, and cheese.
- You don’t need to pass all details at once.
- You can “build” it piece by piece and finally call `.build()` when done.

That’s what Builder Pattern does — makes object creation **flexible, readable, and maintainable**.

---

## Before vs After Builder Pattern

### Before Builder (Using Constructor Overloading)

```java
class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    public User(String name, int age, String email, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }
}

User user = new User("Rashmi", 25, "rashmi@gmail.com", "9876543210");
```

**Problem:**
- Hard to read — what parameter means what?
- Too many constructors for different cases.
- Hard to maintain when object grows.

---

### After Builder Pattern

```java
class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;
        private String phone;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

User user = new User.Builder()
        .name("Rashmi")
        .age(25)
        .email("rashmi@gmail.com")
        .phone("9876543210")
        .build();
```

**More readable**  
**Less error-prone**  
**Optional values can be skipped easily**

---

## Real-World Scenarios of Builder Pattern

Here are **5 easy-to-relate examples** 👇

| Scenario                               | Description |
|----------------------------------------|--------------|
| **House Construction**                 | Build a house step-by-step — foundation, walls, roof, paint — you don’t need all details at once. |
| **Burger Order in a Restaurant**       | Customer customizes burger: cheese/no cheese, extra patty, etc. Builder helps to configure step-by-step. |
| **Car Manufacturing**                  | Different car models built using same steps but different configurations (engine type, color, wheels). |
| **Email Message Creation**             | Build complex email with optional attachments, CC, subject, etc. |
| **API Request Builder (HTTP Clients)** | Example: `HttpRequest.newBuilder().uri(...).header(...).POST(...).build();` in Java 11. |

---

## Advantages & Disadvantages

| ✅ Advantages                                | ❌ Disadvantages                     |
|---------------------------------------------|-------------------------------------|
| Makes object creation **readable and clean**| Adds **more code (Builder class)**  |
| Avoids **telescoping constructors**         | Slightly complex for simple objects |
| Provides **immutability**                   | More boilerplate without Lombok     |
| Easy to maintain & extend                   |                                     |
| Optional fields handled gracefully          |                                     |

---

## Java Example — Complete Implementation

```java
class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private boolean hasGraphicsCard;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM +
               ", storage=" + storage + ", Graphics=" + hasGraphicsCard + "]";
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private boolean hasGraphicsCard;

        public Builder CPU(String CPU) {
            this.CPU = CPU;
            return this;
        }
        public Builder RAM(String RAM) {
            this.RAM = RAM;
            return this;
        }
        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }
        public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .CPU("Intel i9")
                .RAM("32GB")
                .storage("2TB SSD")
                .hasGraphicsCard(true)
                .build();

        System.out.println(gamingPC);
    }
}
```

**Output:**
```
Computer [CPU=Intel i9, RAM=32GB, storage=2TB SSD, Graphics=true]
```

---

## Builder Pattern in Spring Boot — Annotations

In **Spring Boot / Lombok**, you can use:

```java
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private String name;
    private int age;
    private String department;
}
```

### Usage:

```java
Employee emp = Employee.builder()
        .name("Rashmi")
        .age(28)
        .department("HR")
        .build();
```

### Lombok Annotations Related to Builder Pattern

| Annotation         | Description                                            |
|--------------------|--------------------------------------------------------|
| `@Builder`         | Generates builder automatically for the class          |
| `@SuperBuilder`    | Used for inheritance (child + parent fields)           |
| `@Singular`        | Builds collections like `List`, `Set`, `Map` easily    |
| `@Builder.Default` | Sets default value for a field during builder creation |

---

## When to Use Builder Pattern

Use Builder Pattern when:

Object has **many optional fields**  
Object construction is **complex**  
You want **immutability**  
You want **readable chained code**  
You are using **DTOs or POJOs in Spring Boot** (common with Lombok)  
You want to **avoid confusion** with multiple constructors

---

## 📊 Quick Summary Table

| Aspect      | Description                                      |
|-------------|--------------------------------------------------|
| Type        | Creational Pattern                               |
| Purpose     | Step-by-step object creation                     |
| Example     | `User.builder().name("Rashmi").age(25).build();` |
| Real World  | Pizza, Burger, Email, Car, API Request           |
| Common Tool | Lombok `@Builder`                                |
| Benefit     | Clean, readable, flexible code                   |

---

✨ **In short:**  
Builder Pattern = “Step-by-step object construction without messy constructors.”  
It gives you **clean**, **flexible**, and **readable** object creation — especially useful in modern Spring Boot and Lombok projects.

---
