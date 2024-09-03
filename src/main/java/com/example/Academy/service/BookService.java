package com.example.Academy.service;

import com.example.Academy.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    List<Book> getAllBooks();
    void deleteById(Long id);
    Optional<Book> updateById(Long id, Book updatedBookDetails);
    Optional<Book> getBookById(Long id);
    Book createBook(Book book);
}
