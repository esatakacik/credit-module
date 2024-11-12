package com.example.creditmodule.data.service;

import com.example.creditmodule.data.entity.Customer;
import com.example.creditmodule.data.repository.CustomerRepository;
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
