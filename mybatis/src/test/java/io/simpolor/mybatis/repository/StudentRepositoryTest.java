package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.MybatisApplication;
import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisApplication.class)
// @MybatisTest
// @ContextConfiguration(locations = { "classpath:mybatis/mybatis-config.xml" })
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Order(1)
    @Test
    public void testSelectStudentCount(){

        // given, when
        long actual = studentRepository.selectStudentCount();

        // then
        Assertions.assertThat(actual).isEqualTo(2);
    }

    @Order(2)
    @Test
    public void testSelectAllStudent(){

        // given, when
        List<Student> actual = studentRepository.selectAllStudent();

        // than
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
    }

    @Order(3)
    @Test
    public void testSelectStudent(){

        // given
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .build();

        // when
        Student actual = studentRepository.selectStudent(studentId);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getStudentId).isEqualTo(student.getStudentId());
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(Student::getAge).isEqualTo(student.getAge());

    }

    @Order(4)
    @Test
    public void testInsertStudent(){

        // given
        Long studentId = 3L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("사나")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("윙크"))
                .build();


        // when
        int actual = studentRepository.insertStudent(student);

        // then
        Assertions.assertThat(actual).isNotEqualTo(0);
    }

    @Order(5)
    @Test
    public void testDeleteStudent(){

        // given
        Long studentId = 1L;

        // when
        studentRepository.deleteStudent(studentId);

        // then
        Student actual = studentRepository.selectStudent(studentId);
        Assertions.assertThat(actual).isNull();
    }
}
