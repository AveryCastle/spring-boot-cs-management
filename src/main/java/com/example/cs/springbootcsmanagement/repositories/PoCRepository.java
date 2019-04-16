package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.PoC;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoCRepository extends JpaRepository<PoC, String>, PoCRepositoryCustom {

    @EntityGraph(attributePaths = "poCQnATypes")
    @Query("select p from PoC p where p.id = :id")
    List<PoC> findAllEntityGraph(String id);
}
