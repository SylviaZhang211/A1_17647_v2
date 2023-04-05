package edu.cmu.service;

import edu.cmu.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<?> addBook(Book book);

    Book findByISBN(String isbn);

    ResponseEntity<?> updateBook(Book book, String isbn);

    void deleteAll();
}
