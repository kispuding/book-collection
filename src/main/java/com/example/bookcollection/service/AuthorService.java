package com.example.bookcollection.service;

import com.example.bookcollection.model.Book;

import java.util.Set;

public interface AuthorService {
    Set<Book> findAllBooksWrittenByAuthor(Long id);
    void delete(Long id);

}
