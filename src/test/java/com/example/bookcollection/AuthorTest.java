package com.example.bookcollection;

import com.example.bookcollection.exception.AuthorNotFoundException;
import com.example.bookcollection.exception.BookNotFoundException;
import com.example.bookcollection.model.Author;
import com.example.bookcollection.repository.AuthorRepository;
import com.example.bookcollection.repository.BookRepository;
import com.example.bookcollection.service.AuthorService;
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
        AuthorTest.AuthorTestConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorTest {

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Configuration
    @ComponentScan(basePackageClasses = {AuthorService.class, AuthorRepository.class})
    public static class AuthorTestConfig {}

    @Test
    public void testCreate() {
        final Author author = new Author();
        author.setId(1L);
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        final Author savedAuthor = authorRepository.save(author);
        assertNotNull(savedAuthor);
    }

    @Test
    public void testRead() {
        final Author author = new Author();
        author.setId(1L);
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        final Author savedAuthor = authorRepository.save(author);
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));
        assertEquals(savedAuthor.getId(), author.getId());
        assertEquals(savedAuthor.getFirstName(), author.getFirstName());
        assertEquals(savedAuthor.getLastName(), author.getLastName());
    }

    @Test
    public void testUpdate() {
        final Author author = new Author();
        author.setId(1L);
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        final Author savedAuthor = authorRepository.save(author);

        savedAuthor.setFirstName("UpdatedFirstName");
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(savedAuthor));
        final Author updatedAuthor = authorRepository.save(savedAuthor);
        assertTrue("UpdatedFirstName".equals(updatedAuthor.getFirstName()));
    }

    @Test(expected = AuthorNotFoundException.class)
    public void testDelete() {
        final Author author = new Author();
        author.setId(1L);
        author.setFirstName("FirstName");
        author.setLastName("LastName");
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        final Author savedAuthor = authorRepository.save(author);
        Mockito.doThrow(new AuthorNotFoundException()).when(authorRepository).deleteById(savedAuthor.getId());
        authorRepository.deleteById(savedAuthor.getId());
    }

}
