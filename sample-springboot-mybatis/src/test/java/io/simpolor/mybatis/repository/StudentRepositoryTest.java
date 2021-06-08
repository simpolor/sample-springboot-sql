package io.simpolor.mybatis.repository;

import io.simpolor.mybatis.MybatisApplication;
import io.simpolor.mybatis.model.StudentDto;
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
        List<StudentDto> actual = studentRepository.selectStudentList();

        // than
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
    }

    @Test
    public void testSelectStudent(){

        // given
        long seq = 1L;
        StudentDto student = StudentDto.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("달리기"))
                .build();

        // when
        StudentDto actual = studentRepository.selectStudent(seq);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(StudentDto::getSeq).isEqualTo(student.getSeq());
        Assertions.assertThat(actual).extracting(StudentDto::getName).isEqualTo(student.getName());
        Assertions.assertThat(actual).extracting(StudentDto::getAge).isEqualTo(student.getAge());

    }

    @Test
    public void testInsertStudent(){

        // given
        long seq = 3L;
        StudentDto student = StudentDto.builder()
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
    public void testDeleteStudent(){

        // given
        long seq = 1L;

        // when
        studentRepository.deleteStudent(seq);

        // then
        StudentDto actual = studentRepository.selectStudent(seq);
        Assertions.assertThat(actual).isNull();
    }
}
