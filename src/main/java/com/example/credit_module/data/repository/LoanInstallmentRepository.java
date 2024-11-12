package com.example.credit_module.data.repository;

import com.example.credit_module.data.entity.LoanInstallment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanInstallmentRepository extends CrudRepository<LoanInstallment, Long>, JpaSpecificationExecutor<LoanInstallment> {
}
