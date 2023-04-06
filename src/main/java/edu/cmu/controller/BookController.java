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

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/books")
//public class BookController {
//
//    final
//    BookService bookService;
//
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//       //bookService.deleteAll();
//    }
//
//    @Operation(summary = "Add a new book")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Successfully added a new book",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content),
//            @ApiResponse(responseCode = "422", description = "ISBN already exists in the system", content = @Content)
//    })
//    @PostMapping("")
//    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
//        if (book.isPriceInvalid()) {
//            return new ResponseEntity<>(new ErrorMessage("The price of the book is not valid"), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "update a book by ISBN")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
//            @ApiResponse(responseCode = "404", description = "Book Not Found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content)
//    })
//    @PutMapping("/{ISBN}")
//    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, @PathVariable String ISBN) {
//        if (book.isPriceInvalid()) {
//            return new ResponseEntity<>(new ErrorMessage("The price of the book is not valid"), HttpStatus.BAD_REQUEST);
//        }
//        if (bookService.findBook(ISBN) == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(bookService.updateBook(book), HttpStatus.OK);
//
//    }
//
//    @Operation(summary = "retrieve a book by ISBN")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved the book",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
//            @ApiResponse(responseCode = "404", description = "Book Not Found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content)
//    })
//    @GetMapping({"/{ISBN}", "/isbn/{ISBN}"})
//    public ResponseEntity<?> retrieveBook(@PathVariable String ISBN) {
//        var book = bookService.findBook(ISBN);
//        return book == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(book, HttpStatus.OK);
//    }
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> validationError(MethodArgumentNotValidException ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//}
