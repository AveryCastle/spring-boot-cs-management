package com.example.cs.springbootcsmanagement.repositories;

import com.example.cs.springbootcsmanagement.domains.QQnAType;
import com.example.cs.springbootcsmanagement.domains.QnAType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class QnATypeRepositoryCustomImpl extends QuerydslRepositorySupport implements QnATypeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public QnATypeRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        super(QnAType.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<QnAType> findWithSubQnATypesByDynamicParams(List<Long> subQnATypeIds) {
        QQnAType upperQnAType = new QQnAType("upper");
        QQnAType subQnAType = new QQnAType("sub");

        BooleanBuilder booleanBuilder = getDynamicWhere(subQnATypeIds, subQnAType);

        List<QnAType> fetch = jpaQueryFactory
                .select(upperQnAType)
                .from(upperQnAType)
                .where(upperQnAType.upperQnAType.isNull())
                .leftJoin(upperQnAType.subQnATypes, subQnAType).fetchJoin()
                .where(booleanBuilder)
                .orderBy(upperQnAType.expoOrder.asc())
                .fetch();

        return fetch;
    }

    private BooleanBuilder getDynamicWhere(List<Long> subQnATypeIds, QQnAType subQnAType) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!subQnATypeIds.isEmpty()) {
            booleanBuilder.and(subQnAType.id.in(subQnATypeIds));
        }
        return booleanBuilder;
    }
}
