# Factory Method Design Pattern — Real World Example

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

## 🧩 Conceptual Diagram

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

## 🎯 What is Factory Method?

The **Factory Method Pattern** defines an interface for creating objects but lets subclasses or factories decide which class to instantiate.

You don't use `new` directly — instead, you delegate creation to a method (the factory). It provides **flexibility**, **loose coupling**, and **easy extensibility**.

---

## 🧠 Real-World Analogy

Imagine a **Travel Booking System** that can book:
- ✈️ Flights
- 🏨 Hotels
- 🚌 Buses

Multiple partners (TBO, TripJack, MakeMyTrip) support different services. Instead of hardcoding which one to use, the system asks:

> *"Hey factory, give me the correct travel partner for this request."*

The factory chooses the right class dynamically.

---

## 🧩 Conceptual Diagram

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

## ⚙️ Spring Boot Context

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

## 🧭 Flow with Multiple Product Types

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
