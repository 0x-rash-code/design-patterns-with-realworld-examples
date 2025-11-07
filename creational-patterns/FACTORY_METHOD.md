# Factory Method Design Pattern — Real World Example

## What is Factory Method?

The **Factory Method Pattern** defines an interface for creating objects but lets subclasses or factories decide which class to instantiate.

You don't use `new` directly — instead, you delegate creation to a method (the factory). It provides **flexibility**, **loose coupling**, and **easy extensibility**.

---

## Without Factory Method (Problem Example)

```java
class Notification {
    void notifyUser() {}
}

class SMSNotification extends Notification {
    void notifyUser() {
        System.out.println("Sending an SMS notification...");
    }
}

class EmailNotification extends Notification {
    void notifyUser() {
        System.out.println("Sending an Email notification...");
    }
}

public class NotificationService {
    public static void main(String[] args) {
        // Direct object creation — tightly coupled
        Notification n = new SMSNotification();
        n.notifyUser();
    }
}
```

## Problems of without Factory Method:
1. **Tight Coupling**: Client code is tightly coupled to specific classes (`SMSNotification`, `EmailNotification`).
2. **Difficult to Extend**: Adding new notification types requires modifying existing code.
3. **Violation of Open/Closed Principle**: Code is not closed for modification.


## With Factory Method (Problem Example)

Step 1: Define the Product Interface


```java
interface Notification {
    void notifyUser();
}
```

Step 2: Create Concrete Implementations

```java
class SMSNotification extends Notification {
    void notifyUser() {
        System.out.println("Sending an SMS notification...");
    }
}

class EmailNotification extends Notification {
    void notifyUser() {
        System.out.println("Sending an Email notification...");
    }
}

```

Step 3: Create the Factory (with the Factory Method)
```java
class NotificationFactory {
    public static Notification createNotification(String type) {
        if (type.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        } else {
            throw new IllegalArgumentException("Unknown notification type :" + type);
        }
    }
}
```

Step 4: Use the Factory in Client Code

```java
public class NotificationService {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();
        Notification sms = factory.createNotification("SMS");
        sms.notifyUser();
        
        Notification email = factory.createNotification("EMAIL");
        email.notifyUser();
    }
}
```

## Problems Solved with Factory Method:
1. **Loose Coupling**: Client code depends on the `Notification` interface, not concrete classes.
2. **Easy to Extend**: New notification types can be added without modifying existing code.
3. **Adheres to Open/Closed Principle**: Code is open for extension but closed for modification.


## Real-World Analogy

Imagine a **Travel Booking System** that can book:
- ✈️ Flights
- 🏨 Hotels
- 🚌 Buses

Multiple partners (TBO, TripJack, MakeMyTrip) support different services. Instead of hardcoding which one to use, the system asks:

> *"Hey factory, give me the correct travel partner for this request."*

The factory chooses the right class dynamically.

---

## Conceptual Diagram

```
┌───────────────────────────────┐
│        Client Layer           │
│ (Controller / Service Call)   │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│        Factory Method         │
│     getByCode("tbo")          │
└───────────────┬───────────────┘
                │
    ┌───────────┼───────────┐
    ▼           ▼           ▼
┌──────────┐ ┌──────────┐ ┌──────────┐
│ TboFlight│ │ TripJack │ │MakeMyTrip│
│ThirdParty│ │ThirdParty│ │ThirdParty│
│ (Flight) │ │(Bus/Hotel)│ │ (Flight) │
└──────────┘ └──────────┘ └──────────┘
```

---

## Spring Boot Context

### 1️⃣ Common Interface
Defines the contract for all third-party providers.

### 2️⃣ Product Interfaces
- `FlightThirdParty` (extends CommonThirdParty)
- `HotelThirdParty` # 🏭 Factory Method Design Pattern — Real World Example

**Spring Boot: Flight / Hotel / Bus Booking System**

---

## What is Factory Method?

The **Factory Method Pattern** defines an interface for creating objects but lets subclasses or factories decide which class to instantiate.

You don't use `new` directly — instead, you delegate creation to a method (the factory). It provides **flexibility**, **loose coupling**, and **easy extensibility**.

---

## Real-World Analogy

Imagine a **Travel Booking System** that can book:
- ✈️ Flights
- 🏨 Hotels
- 🚌 Buses

Multiple partners (TBO, TripJack, MakeMyTrip) support different services. Instead of hardcoding which one to use, the system asks:

> *"Hey factory, give me the correct travel partner for this request."*

The factory chooses the right class dynamically.

---

## Conceptual Diagram

```
┌───────────────────────────────┐
│        Client Layer           │
│ (Controller / Service Call)   │
└───────────────┬───────────────┘
                │
                ▼
┌───────────────────────────────┐
│        Factory Method         │
│     getByCode("tbo")          │
└───────────────┬───────────────┘
                │
    ┌───────────┼───────────┐
    ▼           ▼           ▼
┌──────────┐ ┌──────────┐ ┌──────────┐
│ TboFlight│ │ TripJack │ │MakeMyTrip│
│ThirdParty│ │ThirdParty│ │ThirdParty│
│ (Flight) │ │(Bus/Hotel)│ │ (Flight) │
└──────────┘ └──────────┘ └──────────┘
```

---

##  Spring Boot Context

### 1️⃣ Common Interface
Defines the contract for all third-party providers.

### 2️⃣ Product Interfaces
- `FlightThirdParty` (extends CommonThirdParty)
- `HotelThirdParty` (extends CommonThirdParty)
- `BusThirdParty` (extends CommonThirdParty)

### 3️⃣ Concrete Implementations
- `TboFlightThirdParty` → implements FlightThirdParty
- `TripFlightThirdParty` → implements FlightThirdParty

### 4️⃣ Factory Class
- `FlightThirdPartyFactory.getByCode(String code)`
- Finds and returns the correct bean (TBO / TripJack / etc.)

### 5️⃣ Client Service
- `FlightSearchService` uses the factory instead of `new`
- *"Get me the correct FlightThirdParty based on session code."*

---

## Execution Flow

```
User Request → FlightSearchController
                      │
                      ▼
              FlightSearchService
                      │
                      ▼
      FlightThirdPartyFactory.getByCode("tbo")
                      │
                      ▼
      TboFlightThirdParty.searchFlights()
                      │
                      ▼
           FlightSearchResponse → returned to user
```

---

## Flow with Multiple Product Types

```
┌──────────────────────────────┐
│      CommonThirdParty        │
│  (Base Interface: getCode()) │
└──────────────┬───────────────┘
               │
    ┌──────────┼──────────┐
    ▼          ▼          ▼
FlightTP    HotelTP     BusTP
    │          │          │
    ▼          ▼          ▼
TboFlight  TripJack   TripJack
ThirdParty ThirdParty ThirdParty
(Flight API) (All)     (All)
```

---

## Factory Method Mapping

| Factory Pattern Role | Your Class |
|---------------------|------------|
| **Product Interface** | CommonThirdParty |
| **Concrete Products** | TboFlightThirdParty, TripJackThirdParty |
| **Creator / Factory Method** | FlightThirdPartyFactory.getByCode() |
| **Client** | FlightSearchService |
| **Dynamic Object Selection** | Based on SessionContext.thirdPartyCode |
| **Extensible Design** | Add new provider, no code change |

---

##  Comparison Table

| Aspect | Without Factory | With Factory Method |
|--------|----------------|---------------------|
| **Object Creation** | Hardcoded `new()` | Done via `getByCode()` |
| **Coupling** | Tight | Loose |
| **Scalability** | Low | High |
| **Open/Closed** | ❌ Violates | ✅ Follows |
| **Testability** | Difficult | Easier |

---



## Summary Diagram

```
┌────────────────────────────┐
│ Controller (Client)        │
└────────────┬───────────────┘
             │
             ▼
┌────────────────────────────┐
│ FlightSearchService        │
└────────────┬───────────────┘
             │
             ▼
┌────────────────────────────┐
│ FlightThirdPartyFactory    │
└────────────┬───────────────┘
      │      │      │
      ▼      ▼      ▼
   TboFlight TripJack MakeMyTrip
   ThirdParty ThirdParty ThirdParty
```

---

### Advantages:
- ✅ Loose Coupling
- ✅ High Scalability
- ✅ Cleaner, Testable Code
- ✅ Open/Closed Principle

---

## ✅ Summary

**Pattern Type:** Creational  
**Factory Method:** `getByCode()`  
**Spring Features Used:** `@Service`, `@Autowired`, Bean Discovery
