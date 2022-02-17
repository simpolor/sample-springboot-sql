package io.simpolor.jdbc.repository;

import io.simpolor.jdbc.JdbcApplication;
import io.simpolor.jdbc.repository.entity.Student;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {JdbcApplication.class})
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSelectCount(){

        // given, when
        Long result = studentRepository.selectCount();

        // then
        MatcherAssert.assertThat(2L, CoreMatchers.is(result));
    }

    @Test
    public void testSelectList(){

        // given, when
        List<Student> actual = studentRepository.selectList();

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
    }

    @Test
    public void testSelectOne(){

        // given
        Long studentId = 1L;

        // when
        Student actual = studentRepository.selectOne(studentId);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo("하니");
    }

    @Test
    public void testInsert(){

        // given
        Long studentId = 3L;
        Student student = new Student(studentId, "사나", 1, 17, Arrays.asList("달리기"));

        // when
        long actual = studentRepository.insert(student);

        // then
        Assertions.assertThat(actual).isEqualTo(3L);
    }

    @Test
    public void testUpdate(){

        // given
        Long studentId = 2L;
        String name = "사니";
        Student student = new Student(studentId, name, 1, 17, Arrays.asList("달리기"));

        // when
        studentRepository.update(student);

        // then
        Student actual = studentRepository.selectOne(studentId);
        Assertions.assertThat(actual.getStudentId()).isEqualTo(2L);
        Assertions.assertThat(actual.getName()).isEqualTo(name);
    }

    @Test
    public void testDelete(){

        // given
        Long studentId = 1L;

        // when
        studentRepository.delete(studentId);

        // then
        Student actual = studentRepository.selectOne(studentId);
        Assertions.assertThat(actual).isNull();
    }
}
