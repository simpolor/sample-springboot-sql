package io.simpolor.querydsl.repository;

import io.simpolor.querydsl.config.QuerydslConfiguration;
import io.simpolor.querydsl.repository.entity.Student;
import io.simpolor.querydsl.repository.querydsl.StudentRepositoryCustom;
import io.simpolor.querydsl.repository.querydsl.StudentRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith({SpringExtension.class})
@DataJpaTest
/*@EnableJpaRepositories(basePackages = {"io.simpolor.querydsl.repository"})*/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(QuerydslConfiguration.class)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        studentRepository.deleteAll();

        Student student = Student.builder()
                .seq(1L)
                .name("하니")
                .grade(1)
                .age(17)
                .build();

        Student student2 = Student.builder()
                .seq(2L)
                .name("철수")
                .grade(2)
                .age(18)
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
        long seq = 1L;
        String name = "하니";
        int grade = 1;
        int age = 17;

        // when
        Optional<Student> actual = studentRepository.findById(seq);

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).get().extracting(Student::getSeq).isEqualTo(seq);
        Assertions.assertThat(actual).get().extracting(Student::getName).isEqualTo(name);
        Assertions.assertThat(actual).get().extracting(Student::getGrade).isEqualTo(grade);
        Assertions.assertThat(actual).get().extracting(Student::getAge).isEqualTo(age);

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
}
