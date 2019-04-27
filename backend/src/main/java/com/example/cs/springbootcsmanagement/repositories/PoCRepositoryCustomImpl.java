package com.example.cs.springbootcsmanagement.repositories;

import static com.example.cs.springbootcsmanagement.domains.QPoC.poC;
import static com.example.cs.springbootcsmanagement.domains.QPoCQnAType.poCQnAType;
import static com.example.cs.springbootcsmanagement.domains.QQnAType.qnAType;

import com.example.cs.springbootcsmanagement.domains.PoC;
import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PoCRepositoryCustomImpl extends QuerydslRepositorySupport implements PoCRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public PoCRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(PoC.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<QnAType> findWithSubTypesOrderByExpoOrder(String pocId) {
        return jpaQueryFactory
                .select(Projections.fields(QnAType.class,
                        qnAType.id, qnAType.title, qnAType.expoOrder.as("ordering"),
                        qnAType.subQnATypes.as("subTypes")))
                .from(poC).where(poC.id.eq(pocId))
                .innerJoin(poC.poCQnATypes, poCQnAType).fetchJoin()
                .innerJoin(poCQnAType.qnaType, qnAType)
                .orderBy(qnAType.expoOrder.asc()).fetch();
    }
}
