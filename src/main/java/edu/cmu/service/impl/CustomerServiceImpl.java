package edu.cmu.service.impl;

import edu.cmu.model.Customer;
import edu.cmu.repository.CustomerRepository;
import edu.cmu.response.ErrorMessage;
import edu.cmu.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository cr;

    public CustomerServiceImpl(CustomerRepository cr){
        this.cr = cr;
    }
    @Override
    public Customer addCustomer(Customer customer) {
        return cr.save(customer);
    }

    @Override
    public Customer findCustomerByID(int ID) {
        return cr.findById(ID).orElse(null);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return cr.findByUserId(email);
    }
}
