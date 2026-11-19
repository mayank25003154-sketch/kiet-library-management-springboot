package com.kiet.library.repository;

import com.kiet.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Repository layer using Java Collections Framework (Map & List)
 * Demonstrating CRUD business logic with Collections
 */
@Repository
public class BookRepository {

    // Using ConcurrentHashMap collection for thread-safe in-memory storage (O(1) lookup by ID)
    private final Map<Integer, Book> bookStorage = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(100);

    public BookRepository() {
        // Pre-populate sample books
        save(new Book(101, "Effective Java", "Joshua Bloch", "Computer Science", 15));
        save(new Book(102, "Clean Code", "Robert C. Martin", "Software Engineering", 8));
        save(new Book(103, "Introduction to Algorithms", "CLRS", "Computer Science", 12));
        save(new Book(104, "Artificial Intelligence: A Modern Approach", "Stuart Russell", "AI", 5));
        save(new Book(105, "Head First Design Patterns", "Eric Freeman", "Software Design", 20));
    }

    // (b) Add a new book
    public Book save(Book book) {
        if (book.getId() == null || book.getId() <= 0) {
            book.setId(idGenerator.incrementAndGet());
        }
        bookStorage.put(book.getId(), book);
        return book;
    }

    // (c) Retrieve all books using Collection values
    public List<Book> findAll() {
        return new ArrayList<>(bookStorage.values());
    }

    // (c) Retrieve a specific book by ID
    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(bookStorage.get(id));
    }

    // (d) Update book details
    public Optional<Book> update(Integer id, Book updatedBook) {
        if (!bookStorage.containsKey(id)) {
            return Optional.empty();
        }
        Book existing = bookStorage.get(id);
        if (updatedBook.getTitle() != null) existing.setTitle(updatedBook.getTitle());
        if (updatedBook.getAuthor() != null) existing.setAuthor(updatedBook.getAuthor());
        if (updatedBook.getCategory() != null) existing.setCategory(updatedBook.getCategory());
        if (updatedBook.getAvailableCopies() != null) existing.setAvailableCopies(updatedBook.getAvailableCopies());
        
        bookStorage.put(id, existing);
        return Optional.of(existing);
    }

    // (e) Delete book by ID
    public boolean deleteById(Integer id) {
        return bookStorage.remove(id) != null;
    }

    public boolean existsById(Integer id) {
        return bookStorage.containsKey(id);
    }
}
