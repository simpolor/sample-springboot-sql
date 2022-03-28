package io.simpolor.querydsl.repository;

import io.simpolor.querydsl.QuerydslConfiguration;
import io.simpolor.querydsl.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({SpringExtension.class})
@DataJpaTest
/*@EnableJpaRepositories(basePackages = {"io.simpolor.querydsl.repository"})*/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Import(QuerydslConfiguration.class)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        /*studentRepository.deleteAll();

        Student student = Student.builder()
                .studentId(1L)
                .name("하니")
                .grade(1)
                .age(17)
                .build();

        Student student2 = Student.builder()
                .studentId(2L)
                .name("철수")
                .grade(2)
                .age(18)
                .build();

        List<Student> students = Lists.newArrayList(student, student2);

        studentRepository.saveAll(students);*/
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
        Integer grade = 1;
        Integer age = 17;

        // when
        Optional<Student> actual = studentRepository.findById(studentId);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getStudentId).isEqualTo(studentId);
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(name);
        Assertions.assertThat(actual).get().extracting(Student::getGrade).isEqualTo(grade);
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(age);

    }

    @Test
    public void testSave(){

        // given
        Long studentId = 3L;
        Student student = Student.builder()
                .studentId(studentId)
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
        Long studentId = 1L;

        // when
        studentRepository.deleteById(studentId);

        // then
        Optional<Student> actual = studentRepository.findById(studentId);
        Assertions.assertThat(actual).isEmpty();
    }
}
