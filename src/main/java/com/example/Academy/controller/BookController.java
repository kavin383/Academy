package com.example.Academy.controller;

import com.example.Academy.Entity.Book;
import com.example.Academy.Entity.Member;
import com.example.Academy.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getMemberById (@PathVariable Long id) {
//        Book book = bookService.getBookById(id);
//        return ResponseEntity.ok(book);
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById (@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateById(@PathVariable Long id,@RequestBody Book updatedBookDetails) {
        Optional<Book> book= bookService.updateById(id,updatedBookDetails);
        return book.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }




}
