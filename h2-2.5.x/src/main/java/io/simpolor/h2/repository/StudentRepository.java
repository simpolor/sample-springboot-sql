package io.simpolor.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.simpolor.h2.repository.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
