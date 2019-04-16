package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.dto.QnATypeDTO;

import java.util.List;

public interface PoCRepositoryCustom {

    List<QnATypeDTO> findWithSubTypesOrderByExpoOrder(String pocId);
}
