package com.example.bookcollection.service;

import com.example.bookcollection.exception.AuthorNotFoundException;
import com.example.bookcollection.exception.BookNotFoundException;
import com.example.bookcollection.model.Author;
import com.example.bookcollection.model.Book;
import com.example.bookcollection.repository.AuthorRepository;
import com.example.bookcollection.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Method to create new book with relation to existing author.
     * In case there is no author with the specified id, an exception is thrown.
     * @param book - Book to be created
     * @return the created book
     */
    @Override
    public Book create(Book book) {
        Set<Author> authorSet = new HashSet<>();
        if (book.getAuthors() != null) {
            for (Author author : book.getAuthors()) {
                Optional<Author> optAuth = authorRepository.findById(author.getId());
                if (optAuth.isPresent()) {
                    authorSet.add(optAuth.get());
                } else {
                    throw new AuthorNotFoundException();
                }
            }
        }
        book.setAuthors(authorSet);
        return bookRepository.save(book);
    }

    /**
     * Method to update an existing book.
     * In case there were previously defined authors of book,
     * they won't get overridden, their relation to book remains,
     * and also new relations are created with the authors defined in this request.
     * @param book - book to be updated
     * @return the updated book
     */
    @Override
    public Book update(Book book) {
        Optional<Book> optBook = bookRepository.findById(book.getId());
        if (optBook.isPresent()) {
            Book updatedBook = optBook.get();
            Set<Author> authors = updatedBook.getAuthors();
            if (!book.getAuthors().isEmpty()) {
                for (Author author : book.getAuthors()) {
                    Optional<Author> a = authorRepository.findById(author.getId());
                    if (a.isPresent()) authors.add(a.get());
                }
            }
            updatedBook.setAuthors(authors);
            return bookRepository.save(updatedBook);
        } else {
            throw new BookNotFoundException();
        }
    }


}
