package com.example.bookcollection.repository;

import com.example.bookcollection.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
