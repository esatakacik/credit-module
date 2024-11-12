package com.example.credit_module.data.mapper;

import com.example.credit_module.data.dto.LoanDTO;
import com.example.credit_module.data.entity.Loan;
import com.example.credit_module.service.Request.RequestCreateLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);
    @Mapping(target = "createDate", expression = "java(getCurrentDate())")
    @Mapping(target = "isPaid", expression = "java(Boolean.FALSE)")
    Loan toEntity(RequestCreateLoan request);

    LoanDTO toDTO(Loan entity);

    List<LoanDTO> toDTO(List<Loan> entityList);

    default Date getCurrentDate(){
        return new Date();
    }
}
