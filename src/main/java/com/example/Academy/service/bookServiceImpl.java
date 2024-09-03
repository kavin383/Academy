package com.example.Academy.service;

import com.example.Academy.Entity.Book;
import com.example.Academy.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class bookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

//    public bookServiceImpl(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> updateById(Long id, Book updatedBookDetails) {
        return bookRepository.findById(id).map(existingBook -> {
        existingBook.setTitle(updatedBookDetails.getTitle());
        existingBook.setAuthor(updatedBookDetails.getAuthor());
        existingBook.setAvailable(updatedBookDetails.getAvailable());
        existingBook.setIsbn(updatedBookDetails.getIsbn());
        existingBook.setCategory(updatedBookDetails.getCategory());
        return bookRepository.save(existingBook);

    });
    }


}
