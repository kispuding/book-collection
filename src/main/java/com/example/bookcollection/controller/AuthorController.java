package com.example.bookcollection.controller;

import com.example.bookcollection.exception.AuthorIdMismatchException;
import com.example.bookcollection.exception.AuthorNotFoundException;
import com.example.bookcollection.exception.BookNotFoundException;
import com.example.bookcollection.model.Author;
import com.example.bookcollection.model.Book;
import com.example.bookcollection.repository.AuthorRepository;
import com.example.bookcollection.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping
    public Iterable findAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author findOneAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);
        authorService.delete(id);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable Long id) {
        if (author.getId() != id) {
            throw new AuthorIdMismatchException();
        }
        authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);
        return authorRepository.save(author);
    }

    @GetMapping("/{id}/books")
    public Set<Book> findBooksOfAuthor(@PathVariable("id") Long id) {
        return authorService.findAllBooksWrittenByAuthor(id);
    }
}
