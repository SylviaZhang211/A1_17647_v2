package edu.cmu.service;

import edu.cmu.model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer findCustomerByID(int ID);

    Customer findCustomerByEmail(String email);
}

