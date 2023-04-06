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
    //BookRepository bookRepository;
    //@Autowired
    BookRepository br;
    public BookServiceImpl(BookRepository bookRepository) {
        this.br = bookRepository;
    }

    @Override
    public Book findBook(String ISBN) {
        return br.findById(ISBN).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return br.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return br.save(book);
    }


}