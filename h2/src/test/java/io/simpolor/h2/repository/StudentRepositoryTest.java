package io.simpolor.h2.repository;

import io.simpolor.h2.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
// @ActiveProfiles("local")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})*/
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCount(){

        // given, when
        long actual = studentRepository.count();

        // then
        Assertions.assertThat(actual).isEqualTo(2);
    }

    @Test
    public void testFindAll(){

        // given, when
        List<Student> actual = studentRepository.findAll();

        // than
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
    }

    @Test
    public void testFindById(){

        // given
        long studentId = 1L;
        Student student = new Student(studentId, "하니", 1, 17);

        // when
        Optional<Student> actual = studentRepository.findById(studentId);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getStudentId).isEqualTo(student.getStudentId());
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(student.getAge());

    }

    @Test
    public void testSave(){

        // given
        long studentId = 3L;
        Student student = new Student(studentId, "사나", 1, 17);

        // when
        Student actual = studentRepository.save(student);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(student.getAge());
    }

    @Test
    public void testDeleteById(){

        // given
        long studentId = 1L;

        // when
        studentRepository.deleteById(studentId);

        // then
        Optional<Student> actual = studentRepository.findById(studentId);
        Assertions.assertThat(actual).isEmpty();
    }
}
