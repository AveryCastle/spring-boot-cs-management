package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.PoCQnAType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoCQnATypeRepository extends JpaRepository<PoCQnAType, Long>, PoCQnATypeRepositoryCustom {
}
