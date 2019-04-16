package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.PoCQnAType;
import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.cs.springbootcsmanagement.domains.QPoCQnAType.poCQnAType;
import static com.example.cs.springbootcsmanagement.domains.QQnAType.qnAType;

public class PoCQnATypeRepositoryCustomImpl extends QuerydslRepositorySupport implements PoCQnATypeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public PoCQnATypeRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(PoCQnAType.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<QnAType> findByPoCIdEntityGraph(String pocId) {
        List<QnAType> fetch = jpaQueryFactory
                .select(qnAType)
                .from(poCQnAType)
                .innerJoin(poCQnAType.qnaType, qnAType)
                .where(poCQnAType.poc.id.eq(pocId))
                .orderBy(qnAType.expoOrder.asc())
                .fetch();

        return fetch;
    }
}
