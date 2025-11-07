# Spring Boot - Gang of Four (GoF) Behavioral Design Patterns

This document maps common **Behavioral Design Patterns** from the Gang of Four to their implementations in **Spring Boot**.

## Behavioral Patterns Overview

| GoF Pattern | Description | Spring Boot Annotation(s) | How It Applies |
|-------------|-------------|---------------------------|----------------|
| **Chain of Responsibility** | Pass request along a chain of handlers. | `@ControllerAdvice`, `@ExceptionHandler`, Servlet `Filter` chain | Each handler can process or pass the request down the chain. |
| **Command** | Encapsulate request as an object. | `@Scheduled`, `@Async`, `ApplicationRunner`, `CommandLineRunner` | Wraps operations as executable tasks (commands). |
| **Iterator** | Sequentially access elements. | (Java-based, not annotation) | Implemented in Spring Data repositories (`findAll()`, pagination). |
| **Mediator** | Central communication hub. | `ApplicationEventPublisher`, `@EventListener` | Centralized event mediator between components. |
| **Memento** | Capture and restore state. | `@SessionAttributes`, `@SessionScope`, `@RequestScope` | Stores and restores bean state per request or session. |
| **Observer** | Notify dependents of state changes. | `@EventListener`, `@ApplicationListener` | Implements event-driven programming — publisher/subscriber model. |
| **State** | Alter behavior when internal state changes. | Spring State Machine, `@StateMachine` (external library) | Represents workflow or finite state transitions. |
| **Strategy** | Define family of algorithms and choose one. | `@Primary`, `@Qualifier`, `@Conditional`, `@Profile` | Selects which bean (algorithm) to use dynamically. |
| **Template Method** | Define algorithm skeleton; subclasses fill in steps. | `JdbcTemplate`, `RestTemplate`, `@Template` classes | Provides reusable algorithm steps; developer overrides specific parts. |
| **Visitor** | Add operations to objects without changing them. | (Rare) Spring uses `BeanPostProcessor`, `BeanFactoryPostProcessor` | Allows adding logic without modifying existing bean definitions. |
| **Interpreter** | Define grammar for language and interpret sentences. | Spring Expression Language (SpEL) — `@Value`, `@ConditionalOnExpression` | Parses and evaluates expressions dynamically at runtime. |


## Key Takeaways

- **Spring Boot extensively uses both structural and behavioral patterns** to provide clean abstractions and powerful features.
- **AOP (Aspect-Oriented Programming)** is the foundation for many patterns like Decorator and Proxy.
- **Event-driven architecture** (Observer pattern) enables loose coupling between components.
- **Template classes** (Template Method pattern) reduce boilerplate code significantly.
- Understanding these patterns helps in designing maintainable and scalable Spring Boot applications.
