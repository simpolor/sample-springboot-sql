package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

}
