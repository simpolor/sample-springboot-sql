package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.MybatisApplication;
import io.simpolor.mybatis.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MybatisApplication.class)
// @MybatisTest
// @ContextConfiguration(locations = { "classpath:mybatis/mybatis-config.xml" })
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSelectStudentTotalCount(){

        // given, when
        long actual = studentRepository.selectStudentTotalCount();

        // then
        Assertions.assertThat(actual).isEqualTo(2);
    }

    @Test
    public void testSelectStudentList(){

        // given, when
        List<Student> actual = studentRepository.selectStudentList();

        // than
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
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
        int actual = studentRepository.insertStudent(student);

        // then
        Assertions.assertThat(actual).isNotEqualTo(0);
    }

    @Test
    public void testDeleteById(){

        // given
        long seq = 1L;

        // when
        studentRepository.deleteStudent(seq);

        // then
        Student actual = studentRepository.selectStudent(seq);
        Assertions.assertThat(actual).isNull();
    }
}
