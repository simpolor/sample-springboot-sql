package io.simpolor.querydsl.repository.querydsl;

import com.querydsl.core.QueryResults;
import io.simpolor.querydsl.repository.entity.Student;

public interface StudentRepositoryCustom {

    QueryResults<Student> search(String name);
}
