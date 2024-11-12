package com.example.credit_module.data.repository;

import com.example.credit_module.data.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
