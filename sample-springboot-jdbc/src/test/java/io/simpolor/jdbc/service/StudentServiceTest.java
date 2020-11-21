package io.simpolor.jdbc.service;

import io.simpolor.jdbc.domain.Student;
import io.simpolor.jdbc.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTotalCount() {

        // given
        long count = 3L;
        when(studentRepository.selectCount()).thenReturn(count);

        // when
        long actual = studentService.getTotalCount();

        // then
        Assertions.assertThat(actual).isEqualTo(3L);
    }

    @Test
    public void testGetAll() {

        // given
        Student student = new Student(1, "홍길동", 2, 18, Arrays.asList("독서"));
        when(studentRepository.selectList()).thenReturn(Arrays.asList(student));

        // when
        List<Student> actual = studentService.getAll();

        // then
        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual).size().isEqualTo(1L);

    }

    @Test
    public void testGet() {

        // given
        long seq = 1;
        Student student = new Student(1, "홍길동", 2, 18, Arrays.asList("독서"));
        when(studentRepository.selectOne(anyLong())).thenReturn(student);

        // when
        Student actual = studentService.get(seq);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo("홍길동");

    }

    @Test
    public void testRegister() {

        // given
        long seq = 1;
        Student student = new Student(0, "홍길동", 2, 18, Arrays.asList("독서"));

        // when
        studentService.register(student);

        // then
        verify(studentRepository, times(1)).insert(any());
    }

    @Test
    public void testModify() {

        // given
        long seq = 1;
        Student student = new Student(seq, "홍길동", 2, 18, Arrays.asList("독서"));

        // when
        studentService.modify(student);

        // then
        verify(studentRepository, times(1)).update(any());
    }

    @Test
    public void testDelete() {

        // given
        long seq = 1;

        // when
        studentService.delete(seq);

        // then
        verify(studentRepository, times(1)).delete(anyLong());
    }
}
