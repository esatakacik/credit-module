package com.example.creditmodule.data.repository;

import com.example.creditmodule.data.entity.LoanInstallment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface LoanInstallmentRepository extends CrudRepository<LoanInstallment, Long>, JpaSpecificationExecutor<LoanInstallment> {
}
