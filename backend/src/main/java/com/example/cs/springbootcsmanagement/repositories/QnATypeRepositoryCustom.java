package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;

import java.util.List;

public interface QnATypeRepositoryCustom {

    List<QnAType> findWithSubQnATypesByDynamicParams(List<Long> subQnATypeIds);
}
