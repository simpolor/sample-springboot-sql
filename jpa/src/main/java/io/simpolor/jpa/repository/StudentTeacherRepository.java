package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {

}
