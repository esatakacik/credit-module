package com.example.creditmodule.data.specification;

import com.example.creditmodule.data.entity.LoanInstallment;
import com.example.creditmodule.service.Request.RequestListInstallments;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanInstallmentSpecification {
    public Specification<LoanInstallment> getLoanInstallments(RequestListInstallments request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getLoanId() != null){
                predicates.add(criteriaBuilder.equal(root.get("loanId"), request.getLoanId()));
            }
            if (request.getIsPaid() != null){
                predicates.add(criteriaBuilder.equal(root.get("isPaid"), request.getIsPaid()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }
}
