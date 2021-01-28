package com.example.bookcollection.service;

import com.example.bookcollection.model.Author;
import com.example.bookcollection.model.Book;
import com.example.bookcollection.repository.AuthorRepository;
import com.example.bookcollection.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

    BookRepository bookRepository;

    AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * Method to find all books written by author.
     * @param id - author's id
     * @return set of books written by author
     */
    @Override
    public Set<Book> findAllBooksWrittenByAuthor(Long id) {
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        return allBooks.stream().filter( book -> {
            for (Author author : book.getAuthors()) {
                if (author.getId() == id) return true;
            }
            return false;
        }).collect(Collectors.toSet());
    }

    /**
     * Method to delete author and all his existing relations to books.
     * @param id - author id
     */
    @Override
    public void delete(Long id) {
        Set<Book> byAuthor = findAllBooksWrittenByAuthor(id);
        Optional<Author> author = authorRepository.findById(id);
        if(!byAuthor.isEmpty() && author.isPresent()) {
            for(Book book : byAuthor) {
                Set<Author> authors = book.getAuthors();
                authors.remove(author.get());
                book.setAuthors(authors);
                bookRepository.save(book);
            }
        }
        authorRepository.deleteById(id);
    }
}
