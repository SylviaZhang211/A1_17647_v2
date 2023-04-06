package edu.cmu.service;

import edu.cmu.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {

    Book findBook(String ISBN);

    Book addBook(Book book);

    Book updateBook(Book book);
}
