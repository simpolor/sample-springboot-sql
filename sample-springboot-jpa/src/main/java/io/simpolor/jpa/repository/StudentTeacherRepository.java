package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {

    List<StudentTeacher> findAllByStudentSeq(Long studentSeq);
}
