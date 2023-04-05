package edu.cmu.service.impl;

import edu.cmu.model.Book;
import edu.cmu.repository.BookRepository;
import edu.cmu.response.ErrorMessage;
import edu.cmu.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    final
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<?> addBook(Book book) {
        if (bookRepository.existsById(book.getISBN())) {
            return new ResponseEntity<>(new ErrorMessage("This ISBN already exists in the system."), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateBook(Book book, String ISBN) {
        book.setISBN(ISBN);
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public Book findByISBN(String ISBN) {
        return bookRepository.findById(ISBN).orElse(null);
    }

}
