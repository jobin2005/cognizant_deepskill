# Hands on 4: Difference Between JPA, Hibernate, and Spring Data JPA

## Java Persistence API (JPA)
- JPA is a specification (JSR 338) for persisting, reading, and managing data from Java objects.
- It defines annotations and interfaces like `@Entity`, `@Table`, `@Id`, `EntityManager`, and `TypedQuery`.
- JPA does not provide a concrete implementation by itself.

## Hibernate
- Hibernate is an ORM tool and one of the popular implementations of JPA.
- It implements the JPA specification and also provides additional features beyond standard JPA.
- Example code using Hibernate directly requires manual session and transaction management:

```java
public Integer addEmployee(Employee employee) {
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;
    try {
        tx = session.beginTransaction();
        employeeID = (Integer) session.save(employee);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return employeeID;
}
```

## Spring Data JPA
- Spring Data JPA is an abstraction layer on top of JPA implementations like Hibernate.
- It does not implement JPA itself; instead it simplifies data access and removes boilerplate code.
- Spring Data JPA integrates with Spring transaction management and provides repository interfaces.

Example repository and service:

```java
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
```

```java
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
```

## Key Differences
- **JPA**: API/specification only.
- **Hibernate**: JPA implementation + additional ORM features.
- **Spring Data JPA**: abstraction over JPA implementations that reduces boilerplate and simplifies repository development.

## Summary
- Use JPA to define persistence contracts.
- Use Hibernate to provide the runtime implementation.
- Use Spring Data JPA to simplify repository and CRUD operations over Hibernate/JPA.

## Spring Data JPA Query Methods
Spring Data JPA can create queries based on method names in repository interfaces.
The project includes examples for:

- `findByNameContaining(String text)` — search by containing text
- `findByNameStartingWith(String prefix)` — filter by starting text
- `findByNameContainingOrderByNameAsc(String text)` — search and sort results
- `findTop3ByNameContaining(String text)` — fetch top results
- `findByCodeGreaterThan(String code)` — search for values greater than a threshold
- `findByCodeLessThan(String code)` — search for values less than a threshold
- `findByCreatedDateBetween(LocalDate start, LocalDate end)` — fetch records between dates

The query method names are parsed by Spring Data JPA and translated into SQL at runtime, which removes the need to write repository implementations manually.
