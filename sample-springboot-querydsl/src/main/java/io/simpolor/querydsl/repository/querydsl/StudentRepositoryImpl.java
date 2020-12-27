package io.simpolor.querydsl.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.simpolor.querydsl.repository.entity.QStudent;
import io.simpolor.querydsl.repository.entity.Student;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public QueryResults<Student> findAllBySearch(String name) {

        return queryFactory.selectFrom(QStudent.student).distinct()
                .fetchResults();
    }
}
