package com.example.creditmodule.data.specification;

import com.example.creditmodule.data.entity.Loan;
import com.example.creditmodule.service.Request.RequestListLoan;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanSpecification {
    public Specification<Loan> getLoan(RequestListLoan request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getCustomerId() != null){
                predicates.add(criteriaBuilder.equal(root.get("customerId"), request.getCustomerId()));
            }
            if (request.getLoanAmount() != null){
                predicates.add(criteriaBuilder.equal(root.get("loanAmount"), request.getLoanAmount()));
            }
            if (request.getNumberOfInstallment() != null){
                predicates.add(criteriaBuilder.equal(root.get("numberOfInstallment"), request.getNumberOfInstallment()));
            }
            if (request.getIsPaid() != null){
                predicates.add(criteriaBuilder.equal(root.get("isPaid"), request.getIsPaid()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
