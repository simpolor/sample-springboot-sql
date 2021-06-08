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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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
        long seq = 1L;
        Student student = new Student(seq, "하니", 1, 17);

        // when
        Optional<Student> actual = studentRepository.findById(seq);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getSeq).isEqualTo(student.getSeq());
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(student.getAge());

    }

    @Test
    public void testSave(){

        // given
        long seq = 3L;
        Student student = new Student(seq, "사나", 1, 17);

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
        long seq = 1L;

        // when
        studentRepository.deleteById(seq);

        // then
        Optional<Student> actual = studentRepository.findById(seq);
        Assertions.assertThat(actual).isEmpty();
    }
}
