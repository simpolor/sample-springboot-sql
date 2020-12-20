package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.Classroom;
import io.simpolor.jpa.repository.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllBySeqIn(List<Long> secs);
}
