package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QnAType;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QnATypeRepository extends JpaRepository<QnAType, Long> {

    @EntityGraph(attributePaths = "subQnATypes")
    @Query("select a from QnAType a where a.upperQnAType is null order by a.expoOrder asc")
    List<QnAType> findAllWithSubQnATypesOrderByExpoOrder();

    @EntityGraph(attributePaths = "subQnATypes")
    @Query("select a from QnAType a where a.id = :id order by a.expoOrder asc")
    Optional<QnAType> findByIdWithSubQnATypesOrderByExpoOrder(@Param("id") Long id);
}
