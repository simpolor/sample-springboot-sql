package io.simpolor.jpa.repository;

import io.simpolor.jpa.domain.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

}
