package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;

import java.util.List;

public interface PoCRepositoryCustom {

    List<QnAType> findWithSubTypesOrderByExpoOrder(String pocId);
}
