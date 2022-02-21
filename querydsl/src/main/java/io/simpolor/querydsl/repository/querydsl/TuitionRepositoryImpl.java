package io.simpolor.querydsl.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.simpolor.querydsl.repository.entity.Tuition;
import io.simpolor.querydsl.repository.group.TuitionGroup;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

import static io.simpolor.querydsl.repository.entity.QTuition.tuition;

@RequiredArgsConstructor
public class TuitionRepositoryImpl implements TuitionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tuition> findAllByOrderSeqDesc() {

        return queryFactory
                .selectFrom(tuition)
                .where(tuition.fees.isNotNull())
                .orderBy(tuition.tuitionId.desc())
                .fetch();
    }

    @Override
    public List<TuitionGroup> findAllByStudentGroup() {

        return queryFactory
                .from(tuition)
                .select(Projections.constructor(
                        TuitionGroup.class,
                        tuition.student.studentId,
                        tuition.student.name,
                        tuition.fees.sum().coalesce(0L)
                ))
                .groupBy(tuition.student)
                .fetch();
    }

    /**
     * name이 null인 경우 전체 검색이 된다.
     * @param name
     * @return
     */
    private BooleanExpression isEqualName(String name) {

        if (Objects.isNull(name)){
            return null;
        }

        return tuition.student.name.eq(name);
    }

    /**
     * QueryDSL 함수
     * - select, selectFrom, from
     * - join, leftJoin, on, fetchJoin
     * - where
     * - orderBy
     * - groupBy
     * - offset, limit
     */

    /**
     * 결과 조회 함수
     * - fetchOne : 단건 조회 ( 두개 이상 에러 )
     * - fetchFirst : Optional 단건 조회 ( 두개 이상 에러 )
     * - fetch : 목록 조회
     * - fetchResults : 페이징을 위한 목록 조회 ( 전체 카운트 포함 )
     */

    /**
     * 조건 함수
     * : 표현식이 null인 경우 조건에서 제외
     * - isNull, isNotnull
     * - asc, desc : orderBy에 의존
     * - eq, not, ne, like : like 사용시 검색 단어의 양쪽으로 '%'가 필요
     * - gt, goe, lt, loe
     * - before, after, between
     * - in, notIn
     * - and, or
     */

    /***
     * Projections.constructor 함수
     * - sum
     * - coalesce : 값이 없는 경우 대체
     */

    /**
     * 데이터 포맷 표현식
     * Expression<String> groupDateExpr =
     *     Expressions.stringTemplate("DATE_FORMAT({0}, {1})", student.createdAt, "%Y-%m-%d");
     */

    /**
     * 정렬 순서 정의
     * OrderSpecifier<String> groupDateExprDesc =
     *     Expressions.stringTemplate("DATE_FORMAT({0}, {1})", student.createdAt, "%Y-%m-%d").desc()
     */


}
