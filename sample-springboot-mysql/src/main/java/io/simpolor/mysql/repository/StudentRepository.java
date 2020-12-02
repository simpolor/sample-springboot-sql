package io.simpolor.mysql.repository;

import io.simpolor.mysql.domain.Classroom;
import io.simpolor.mysql.domain.Student;
import io.simpolor.mysql.domain.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value =
            "SELECT s.seq as seq, s.name as name, c.class_name as className\n" +
                    "FROM student s\n" +
                    "LEFT OUTER JOIN student_classroom sc ON s.seq = sc.student_seq\n" +
                    "LEFT OUTER JOIN classroom c ON sc.classroom_seq = c.seq\n",
            nativeQuery = true)
    List<StudentsInterface> selectStudents();
    // EventDateCountInt findEventsInfo(@Param("today") String today);

    public interface StudentsInterface {

        Long getSeq();
        String getName();
        String getClassName();
        Student getStudent();
        Classroom getClassroom();
    }
}
