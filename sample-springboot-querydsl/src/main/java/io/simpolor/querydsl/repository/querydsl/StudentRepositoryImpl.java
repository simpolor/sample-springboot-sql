package io.simpolor.querydsl.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.simpolor.querydsl.repository.entity.Student;
import lombok.RequiredArgsConstructor;

import static io.simpolor.querydsl.repository.entity.QStudent.student;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public QueryResults<Student> search(String name) {

        return queryFactory
                .selectFrom(student)
                .where(student.name.like("%"+name+"%"))
                .fetchResults();
    }
}
