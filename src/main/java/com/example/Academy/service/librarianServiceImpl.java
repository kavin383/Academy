package com.example.Academy.service;

import com.example.Academy.Entity.Librarian;
import com.example.Academy.Repository.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class librarianServiceImpl implements LibrarianService {

    @Autowired
    private final LibrarianRepository librarianRepository;

//    public librarianServiceImpl(LibrarianRepository librarianRepository) {
//        this.librarianRepository = librarianRepository;
//    }

    @Override
    public List<Librarian> getAllLibrarian() {
        return librarianRepository.findAll();
    }

    @Override
    public Optional<Librarian> getLibrarianById(Long id) {
        return librarianRepository.findById(id);
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    @Override
    public Optional<Librarian> updateById(Long id,Librarian updatedLibrarian) {
        Optional<Librarian> existingLibrarian = librarianRepository.findById(id);
        if (existingLibrarian.isPresent()) {
            Librarian librarianToUpdate = existingLibrarian.get();
            librarianToUpdate.setUserName(updatedLibrarian.getUserName());
            librarianToUpdate.setPassword(updatedLibrarian.getPassword());
            librarianRepository.save(librarianToUpdate);
            return Optional.of(librarianToUpdate);
        } else {
        return Optional.empty();
    }
}

    @Override
    public Optional<Librarian> deleteById(Long id) {
        Optional<Librarian> librarian = librarianRepository.findById(id);
        if (librarian.isPresent()) {
            librarianRepository.deleteById(id);
        }
        return librarian;
    }

//    @Override
//    public Optional<Librarian> updateById(Long id) {
//        return Optional.empty();
//    }
}


