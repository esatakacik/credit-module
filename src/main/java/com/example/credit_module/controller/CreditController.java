package com.example.credit_module.controller;

import com.example.credit_module.data.dto.LoanDTO;
import com.example.credit_module.data.dto.LoanInstallmentDTO;
import com.example.credit_module.service.LoanService;
import com.example.credit_module.service.Request.RequestCreateLoan;
import com.example.credit_module.service.Request.RequestListInstallments;
import com.example.credit_module.service.Request.RequestListLoan;
import com.example.credit_module.service.Request.RequestPayLoan;
import com.example.credit_module.service.response.ResponsePayLoan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/credit")
@RequiredArgsConstructor
public class CreditController {
    private final LoanService loanService;

    @PostMapping(path = "/create-loan")
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody RequestCreateLoan request){
        return new ResponseEntity<>(loanService.createLoan(request), HttpStatus.CREATED);
    }

    @PostMapping(path = "/list-loans")
    public ResponseEntity<List<LoanDTO>> listLoans(@Valid @RequestBody RequestListLoan request){
        return new ResponseEntity<>(loanService.listLoan(request), HttpStatus.OK);
    }

    @PostMapping(path = "/list-installments")
    public ResponseEntity<List<LoanInstallmentDTO>> listInstallments(@Valid @RequestBody RequestListInstallments request){
        return new ResponseEntity<>(loanService.listInstallments(request), HttpStatus.OK);
    }

    @PostMapping(path = "/pay-loan")
    public ResponseEntity<ResponsePayLoan> payLoan(@Valid @RequestBody RequestPayLoan request){
        return new ResponseEntity<>(loanService.payLoan(request), HttpStatus.OK);
    }
}
