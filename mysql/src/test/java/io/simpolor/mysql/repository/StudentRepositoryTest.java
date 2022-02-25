package io.simpolor.mysql.repository;

import io.simpolor.mysql.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

// @ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})*/
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

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
        long id = 1L;
        Student student = Student.builder()
                .studentId(id)
                .name("하니")
                .grade(1)
                .age(17)
                .build();

        // when
        Optional<Student> actual = studentRepository.findById(id);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getStudentId).isEqualTo(student.getStudentId());
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(student.getAge());

    }

    @Test
    public void testSave(){

        // given
        long id = 3L;
        Student student = Student.builder()
                .studentId(id)
                .name("사나")
                .grade(1)
                .age(17)
                .build();


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
        long id = 1L;

        // when
        studentRepository.deleteById(id);

        // then
        Optional<Student> actual = studentRepository.findById(id);
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    public void testSelectStudent(){

        // given
        long id = 1L;
        Student student = Student.builder()
                .studentId(id)
                .name("하니")
                .grade(1)
                .age(17)
                .build();

        // when
        Student actual = studentRepository.getOne(id);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getStudentId).isEqualTo(student.getStudentId());
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(student.getAge());

    }
}
