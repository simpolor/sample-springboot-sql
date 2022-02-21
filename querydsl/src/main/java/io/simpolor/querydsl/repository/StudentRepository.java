package io.simpolor.querydsl.repository;

import io.simpolor.querydsl.repository.entity.Student;
import io.simpolor.querydsl.repository.querydsl.StudentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryCustom {

}
