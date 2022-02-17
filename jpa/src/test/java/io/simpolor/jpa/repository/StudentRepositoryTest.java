package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @ActiveProfiles("local")
/*@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})*/
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();

        Student student = Student.builder()
                .studentId(1L)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .favoriteFoods(Collections.EMPTY_SET)
                .build();

        Student student2 = Student.builder()
                .studentId(2L)
                .name("철수")
                .grade(2)
                .age(18)
                .hobbies(Arrays.asList("축구"))
                .favoriteFoods(Collections.EMPTY_SET)
                .build();

        List<Student> students = Lists.newArrayList(student, student2);

        studentRepository.saveAll(students);
    }

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
        Long studentId = 1L;
        String name = "하니";
        Integer age = 17;

        // when
        Optional<Student> actual = studentRepository.findById(studentId);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getStudentId).isEqualTo(studentId);
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(name);
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(age);

    }

    @Test
    public void testSave(){

        // given
        long studentId = 3L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("영희")
                .grade(3)
                .age(18)
                .hobbies(Arrays.asList("독서"))
                .favoriteFoods(Collections.EMPTY_SET)
                .build();

        // when
        Student actual = studentRepository.save(student);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getStudentId).isEqualTo(student.getStudentId());
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(student.getAge());
    }

    @Test
    public void testDeleteById(){

        // given
        long studentId = 2L;

        // when
        studentRepository.deleteById(studentId);

        // then
        Optional<Student> actual = studentRepository.findById(studentId);
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    public void testSelectStudent(){

        // given
        Long studentId = 1L;
        String name = "하니";
        Integer age = 17;

        // when
        Student actual = studentRepository.selectStudent(studentId);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getStudentId).isEqualTo(studentId);
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(name);
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(age);

    }
}
