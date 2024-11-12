package com.example.credit_module.data.mapper;

import com.example.credit_module.data.dto.LoanInstallmentDTO;
import com.example.credit_module.data.entity.LoanInstallment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanInstallmentMapper {
    LoanInstallmentMapper INSTANCE = Mappers.getMapper(LoanInstallmentMapper.class);
    LoanInstallmentDTO toDTO(LoanInstallment entity);
    List<LoanInstallmentDTO> toDTO(List<LoanInstallment> entity);
}
