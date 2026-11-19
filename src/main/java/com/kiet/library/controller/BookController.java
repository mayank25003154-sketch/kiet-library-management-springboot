package com.kiet.library.controller;

import com.kiet.library.model.Book;
import com.kiet.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller for Library Management System
 * Course Outcome: CO5 (Q2)
 */
@RestController
@RequestMapping({"/books", "/api/books"})
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // (b) Add a new book using @PostMapping and @RequestBody
    @PostMapping
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "CREATED");
        response.put("message", "Book added successfully to the library collection.");
        response.put("book", savedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // (c) Retrieve all books using @GetMapping
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    // (c) Retrieve a specific book by its ID using @GetMapping
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("status", "NOT_FOUND");
            error.put("message", "Book not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // (d) Update a book's details using @PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        Optional<Book> updated = bookRepository.update(id, book);
        if (updated.isPresent()) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "SUCCESS");
            response.put("message", "Book details updated successfully.");
            response.put("book", updated.get());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("status", "NOT_FOUND");
            error.put("message", "Cannot update: Book not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    // (e) Delete a book by its ID using @DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Integer id) {
        Map<String, Object> response = new LinkedHashMap<>();
        boolean deleted = bookRepository.deleteById(id);

        if (deleted) {
            response.put("status", "SUCCESS");
            response.put("message", "Book with ID " + id + " was deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "NOT_FOUND");
            response.put("message", "Cannot delete: Book not found with ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
