package com.example.bookcollection.repository;

import com.example.bookcollection.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}