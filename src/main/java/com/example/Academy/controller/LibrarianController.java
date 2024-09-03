package com.example.Academy.controller;

import com.example.Academy.Entity.Librarian;
import com.example.Academy.Entity.Member;
import com.example.Academy.service.LibrarianService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/librarian")
public class LibrarianController {

    @Autowired
    private final LibrarianService librarianService;


    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping
    public List<Librarian> getAllLibrarian() {

        return librarianService.getAllLibrarian();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Librarian> getLibrarianById(@PathVariable Long id) {
        Optional<Librarian> librarian = librarianService.getLibrarianById(id);
        return librarian.map(ResponseEntity::ok).orElseGet
                (()->ResponseEntity.noContent().build());
    }

    @PostMapping
    public Librarian createLibrarian(@RequestBody Librarian librarian) {
        return librarianService.createLibrarian(librarian);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Librarian> deleteById(@PathVariable Long id){
        Optional<Librarian> librarian = librarianService.deleteById(id);
        if(librarian.isPresent()){
            librarianService.deleteById(id);
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Librarian> updateById(@PathVariable Long id,@RequestBody Librarian librarian){
        Optional<Librarian> librarian1 = librarianService.updateById(id, librarian);
        return librarian1.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}
