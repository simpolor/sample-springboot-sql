package io.simpolor.mysql.repository;

import io.simpolor.mysql.domain.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

}
