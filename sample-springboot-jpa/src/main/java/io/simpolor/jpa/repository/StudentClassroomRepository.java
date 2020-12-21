package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

    List<StudentClassroom> getAllByStudentSeq(Long seq);
}
