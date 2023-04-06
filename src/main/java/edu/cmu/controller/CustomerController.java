package edu.cmu.controller;

import edu.cmu.model.Customer;
import edu.cmu.service.CustomerService;
import edu.cmu.utils.Validation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    final
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult) {
        log.info(customer.toString());
        log.info(bindingResult.getAllErrors().toString());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("Missing information"), HttpStatus.BAD_REQUEST);
        } else if (!Validation.checkState(customer.getState())) {
            return new ResponseEntity<>(new org.springdoc.api.ErrorMessage("Malformed input: state"), HttpStatus.BAD_REQUEST);
        } else if (customerService.findCustomerByEmail(customer.getUserId()) != null) {
            return new ResponseEntity<>(new ErrorMessage("This user ID already exists in the system."), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }


    @GetMapping({"/{id}"})
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        var customer = customerService.findCustomerByID(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam("userId") String userId) {
        userId = userId.replace("%40", "@");
        var customer = customerService.findCustomerByEmail(userId);
        if (!Validation.checkEmail(userId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.validation.ConstraintViolationException;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//@Validated
//@RestController
//@RequestMapping("/customers")
//public class CustomerController {
//
//    final
//    CustomerService customerService;
//
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//        //customerService.deleteAll();
//    }
//
//    @Operation(summary = "Add a new customer")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Successfully added a new customer",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content),
//            @ApiResponse(responseCode = "422", description = "User-ID already exists in the system", content = @Content)
//    })
//    @PostMapping("")
//    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
//        if (customer.isStateInvalid()) {
//            return new ResponseEntity<>(new ErrorMessage("The state of the customer is not valid"), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
//    }
//
//    @Operation(summary = "retrieve a customer by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved the customer",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
//            @ApiResponse(responseCode = "404", description = "Customer Not Found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content)
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<?> retrieveCustomerById(@PathVariable int id) {
//        var customer = customerService.findCustomerByID(id);
//        return customer == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(customer, HttpStatus.OK);
//    }
//
//    @Operation(summary = "retrieve a customer by userId")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Successfully retrieved the customer",
//                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class)) }),
//            @ApiResponse(responseCode = "404", description = "Customer Not Found", content = @Content),
//            @ApiResponse(responseCode = "400", description = "Illegal, missing, or malformed input", content = @Content)
//    })
//    @GetMapping("")
//    public ResponseEntity<?> retrieveCustomerByUserId(@RequestParam @Email String userId) {
//        var customer = customerService.findCustomerByEmail(userId);
//        return customer == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(customer, HttpStatus.OK);
//    }
//
//    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
//    public ResponseEntity<?> validationError(Exception ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//}
