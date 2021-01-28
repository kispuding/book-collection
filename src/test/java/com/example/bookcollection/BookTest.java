package com.example.bookcollection;

import com.example.bookcollection.exception.BookNotFoundException;
import com.example.bookcollection.model.Book;
import com.example.bookcollection.repository.AuthorRepository;
import com.example.bookcollection.repository.BookRepository;
import com.example.bookcollection.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@ContextConfiguration(classes =
        BookTest.BookTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @Configuration
    @ComponentScan(basePackageClasses = {BookService.class})
    public static class BookTestConfig {}

    @Test
    public void testCreate() {
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book Title");
        book.setDescription("Test Book description.");
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        final Book savedBook = bookService.create(book);
        assertNotNull(savedBook);
    }

    @Test
    public void testRead() {
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book Title");
        book.setDescription("Test Book description.");
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        final Book bookToRead = bookService.create(book);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(bookToRead));
        assertEquals(bookToRead.getId(), book.getId());
        assertEquals(bookToRead.getTitle(), book.getTitle());
        assertEquals(bookToRead.getDescription(), book.getDescription());
    }

    @Test
    public void testUpdate() {
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book Title");
        book.setDescription("Test Book description.");
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        final Book bookToBeUpdated = bookService.create(book);

        bookToBeUpdated.setTitle("Updated Test Book Title");
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(bookToBeUpdated));
        final Book updatedBook = bookService.update(bookToBeUpdated);
        assertTrue("Updated Test Book Title".equals(updatedBook.getTitle()));
    }

    @Test(expected = BookNotFoundException.class)
    public void testDelete() {
        final Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book Title");
        book.setDescription("Test Book description.");
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        final Book bookToBeDeleted = bookService.create(book);
        Mockito.doThrow(new BookNotFoundException()).when(bookRepository).deleteById(bookToBeDeleted.getId());
        bookRepository.deleteById(bookToBeDeleted.getId());
    }

}
