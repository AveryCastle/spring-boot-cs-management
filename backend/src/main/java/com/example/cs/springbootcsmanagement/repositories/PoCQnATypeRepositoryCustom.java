package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;

import java.util.List;

public interface PoCQnATypeRepositoryCustom {

    List<QnAType> findByPoCIdEntityGraph(String pocId);
}
