package com.example.Academy.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private Set<Book> books = new HashSet<>();
//    private List<Book> book;

    public void addBook(Book book){
        this.books.add(book);
        book.setCategory(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.setCategory(null);

    }
}
