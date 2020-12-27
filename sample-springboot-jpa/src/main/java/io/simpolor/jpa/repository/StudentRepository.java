package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s.* FROM student s WHERE seq = ?1", nativeQuery = true)
    Student selectStudent(long seq);
}
