package com.example.bookcollection.repository;

import com.example.bookcollection.model.Book;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    public void testCreate() {
        final Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book Title");
        book.setDescription("Test Book description.");


    }
}
