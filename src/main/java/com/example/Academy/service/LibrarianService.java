package com.example.Academy.service;

import com.example.Academy.Entity.Librarian;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LibrarianService {
    List<Librarian> getAllLibrarian();
    Optional<Librarian> getLibrarianById(Long id);
    Librarian createLibrarian(Librarian librarian);
    Optional<Librarian> deleteById(Long id);
    Optional<Librarian> updateById(Long id,Librarian updatedLibrarian);
}
