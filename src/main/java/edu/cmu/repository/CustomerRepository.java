package edu.cmu.repository;

import edu.cmu.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByUserId(String email);
}
