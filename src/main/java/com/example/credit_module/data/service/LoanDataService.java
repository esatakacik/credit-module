package com.example.credit_module.data.service;

import com.example.credit_module.data.entity.Loan;
import com.example.credit_module.data.repository.LoanRepository;
import com.example.credit_module.data.specification.LoanSpecification;
import com.example.credit_module.service.Request.RequestListLoan;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanDataService {
    private final LoanRepository repository;
    private final LoanSpecification loanSpecification;

    public Loan save(Loan loan){
        return repository.save(loan);
    }

    public List<Loan> findAll(RequestListLoan request){
        return repository.findAll(loanSpecification.getLoan(request), Sort.by("createDate"));
    }

    public Optional<Loan> findById(Long id){ return repository.findById(id);}

    public Optional<Loan> findByIdAndIsPaid(Long id, Boolean isPaid){ return repository.findByIdAndIsPaid(id, isPaid);}
}
