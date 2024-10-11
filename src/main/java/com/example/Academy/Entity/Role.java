package com.example.Academy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
In a library management system, concurrency management ensures that multiple users can perform operations (like borrowing or returning books) on the same resources (books) without causing conflicts or inconsistencies. Here are a few common scenarios where concurrency management would be important:

Example Scenarios for Concurrency Management:

1. Borrowing the Last Copy of a Book:

Multiple users try to borrow the last available copy of a book at the same time. Without proper concurrency control, it's possible for two users to borrow the same book, resulting in the system showing incorrect availability.



2. Returning a Book and Updating Inventory:

When multiple users are returning books, the system needs to update the book inventory without one user's return overwriting another's.



3. Modifying User Account Information:

If two users or two different processes are updating a user's account (e.g., borrowing history), their changes might overwrite each other unless concurrency control is in place.



4. Book Reservations:

A user might want to reserve a book that another user is currently borrowing. Handling these reservations properly requires managing concurrent requests.





---

Spring Boot CRUD Example with Concurrency Management

Let's implement a simple concurrency management solution using Optimistic Locking. This approach works well when conflicts are rare and helps to prevent lost updates when multiple users are trying to modify the same resource.

We'll use JPA with a @Version field to implement optimistic locking. This field will automatically increment with each update, and JPA will throw an exception if two users try to update the same record concurrently.

Step 1: Create the Book Entity

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String author;
    private int availableCopies;

    @Version
    private int version;  // Optimistic Locking
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
}

Here, the @Version annotation ensures that JPA will handle the optimistic locking for us. Every time an update occurs, the version is incremented.

Step 2: Book Repository

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query to fetch books by title, author, etc.
}

Step 3: Book Service

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book borrowBook(Long bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found"));
        
        if (book.getAvailableCopies() <= 0) {
            throw new Exception("No copies available");
        }
        
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return bookRepository.save(book);
    }
    
    @Transactional
    public Book returnBook(Long bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new Exception("Book not found"));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return bookRepository.save(book);
    }
}

The borrowBook method checks if copies are available and decrements the available copy count.

The returnBook method increments the available copy count when a book is returned.

The @Transactional ensures that these operations happen atomically.


Step 4: Handle Optimistic Locking Exception

When concurrent requests happen, Spring Data JPA will throw an OptimisticLockingFailureException if two users try to update the same record at the same time. You can handle this exception at the service layer.

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConcurrencyExceptionHandler {

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public String handleOptimisticLockingFailure(OptimisticLockingFailureException e) {
        return "Conflict occurred while updating the resource. Please try again.";
    }
}

This ensures that if two users try to borrow the last copy at the same time, one of them will receive an error, and the system will maintain consistency.


---

Conclusion:

By using optimistic locking in a Spring Boot CRUD application, you can efficiently manage concurrency in scenarios like borrowing books or updating book inventory. This approach is ideal when conflicts are rare, as it avoids locking resources and only raises conflicts when necessary.

For higher contention, you could also explore pessimistic locking or use more advanced techniques like eventual consistency with distributed systems, depending on your specific needs.
