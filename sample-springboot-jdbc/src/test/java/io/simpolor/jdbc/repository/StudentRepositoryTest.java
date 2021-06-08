package io.simpolor.jdbc.repository;

import io.simpolor.jdbc.JdbcApplication;
import io.simpolor.jdbc.model.StudentDto;
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
        long result = studentRepository.selectCount();

        // then
        MatcherAssert.assertThat(2L, CoreMatchers.is(result));
    }

    @Test
    public void testSelectList(){

        // given, when
        List<StudentDto> actual = studentRepository.selectList();

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(2);
    }

    @Test
    public void testFind(){

        // given
        long seq = 1L;

        // when
        StudentDto actual = studentRepository.selectOne(seq);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(StudentDto::getName).isEqualTo("하니");
    }

    @Test
    public void testInsert(){

        // given
        long seq = 3L;
        StudentDto student = new StudentDto(seq, "사나", 1, 17, Arrays.asList("달리기"));

        // when
        long actual = studentRepository.insert(student);

        // then
        Assertions.assertThat(actual).isEqualTo(3L);
    }

    @Test
    public void testUpdate(){

        // given
        long seq = 3L;
        StudentDto student = new StudentDto(seq, "사니", 1, 17, Arrays.asList("달리기"));

        // when
        int actual = studentRepository.update(student);

        // then
        Assertions.assertThat(actual).isEqualTo(1);
    }

    @Test
    public void testDelete(){

        // given
        long seq = 1L;

        // when
        studentRepository.delete(seq);

        // then
        StudentDto actual = studentRepository.selectOne(seq);
        Assertions.assertThat(actual).isNull();
    }
}
