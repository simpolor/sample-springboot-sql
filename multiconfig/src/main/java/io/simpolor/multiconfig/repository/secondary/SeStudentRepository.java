package io.simpolor.multiconfig.repository.secondary;

import io.simpolor.multiconfig.repository.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeStudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByName(String name);
}
