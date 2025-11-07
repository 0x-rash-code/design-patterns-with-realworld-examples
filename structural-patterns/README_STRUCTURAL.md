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
