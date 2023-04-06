package edu.cmu.controller;

import edu.cmu.model.Book;
import edu.cmu.model.Customer;
import edu.cmu.service.BookService;
import edu.cmu.utils.Validation;
import jakarta.validation.Valid;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    final
    BookService bookService ;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("Missing information"), HttpStatus.BAD_REQUEST);
        } else if(bookService.findBook(book.getISBN()) != null){
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("This ISBN already exists in the system."), HttpStatus.UNPROCESSABLE_ENTITY);
        }else if (Validation.checkPrice(book.getPrice())) {
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("Malformed input: price"), HttpStatus.BAD_REQUEST);
        }
        //response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @PutMapping("{ISBN}")
    public ResponseEntity<?> updateBook(@PathVariable String ISBN, @Valid @RequestBody Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("Missing information"), HttpStatus.BAD_REQUEST);
        } else if(bookService.findBook(book.getISBN()) == null){
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("ISBN not found"), HttpStatus.NOT_FOUND);
        }else if (Validation.checkPrice(book.getPrice())) {
            return new ResponseEntity<>(new ErrorMessage("Malformed input: price"), HttpStatus.BAD_REQUEST);
        }
        //response.setHeader("Location", request.getRequestURL().append("/").append(createdHotel.getId()).toString());
        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);

    }

    @GetMapping({"isbn/{ISBN}","{ISBN}"})
    public ResponseEntity<?> getBook(@PathVariable String ISBN){
        var book = bookService.findBook(ISBN);
        if (book != null){

            return new ResponseEntity<>(book, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


