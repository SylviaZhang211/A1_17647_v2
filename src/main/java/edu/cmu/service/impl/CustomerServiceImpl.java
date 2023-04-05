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

    final
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<?> addCustomer(Customer customer) {
        if (customerRepository.existsByUserId(customer.getUserId())) {
            return new ResponseEntity<>(new ErrorMessage("This user ID already exists in the system."), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findByUserId(String userId) {
        return customerRepository.findCustomerByUserId(userId);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
