package io.simpolor.jpa.repository;

import io.simpolor.jpa.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
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
    public void testTotalCount(){

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
        Student student = Student.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("달리기"))
                .build();

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
        Student student = Student.builder()
                .seq(seq)
                .name("사나")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("윙크"))
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
        long seq = 1L;

        // when
        studentRepository.deleteById(seq);

        // then
        Optional<Student> actual = studentRepository.findById(seq);
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    public void testSelectStudent(){

        // given
        long seq = 1L;
        Student student = Student.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("달리기"))
                .build();

        // when
        Student actual = studentRepository.selectStudent(seq);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getSeq).isEqualTo(student.getSeq());
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(student.getAge());

    }
}
