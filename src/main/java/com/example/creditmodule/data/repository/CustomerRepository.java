package com.example.creditmodule.data.repository;

import com.example.creditmodule.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
