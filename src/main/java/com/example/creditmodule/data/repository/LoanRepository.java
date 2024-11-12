package com.example.creditmodule.data.repository;

import com.example.creditmodule.data.entity.Loan;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {
    Optional<Loan> findByIdAndIsPaid(Long id, Boolean isPaid);
}
