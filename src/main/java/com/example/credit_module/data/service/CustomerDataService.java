package com.example.credit_module.data.service;

import com.example.credit_module.data.entity.Customer;
import com.example.credit_module.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerDataService {
    private final CustomerRepository repository;

    public Optional<Customer> findById(Long id){
        return repository.findById(id);
    }

    public Customer save(Customer customer){ return repository.save(customer); }
}
