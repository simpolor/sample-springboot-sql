package io.simpolor.jdbc.service;

import io.simpolor.jdbc.repository.StudentRepository;
import io.simpolor.jdbc.repository.entity.Student;
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
import static org.mockito.Mockito.*;

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
    public void testGetAll() {

        // given
        Student student = new Student(1L, "홍길동", 2, 18, Arrays.asList("독서"));
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
        Long studentId = 1L;
        Student student = new Student(studentId, "홍길동", 2, 18, Arrays.asList("독서"));
        when(studentRepository.selectOne(anyLong())).thenReturn(student);

        // when
        Student actual = studentService.get(studentId);

        // then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual).extracting(Student::getName).isEqualTo("홍길동");

    }

    @Test
    public void testCreate() {

        // given
        Long studentId = 1L;
        Student student = new Student(0L, "홍길동", 2, 18, Arrays.asList("독서"));

        // when
        studentService.create(student);

        // then
        verify(studentRepository, times(1)).insert(any());
    }

    @Test
    public void testUpdate() {

        // given
        Long studentId = 1L;
        Student student = new Student(studentId, "홍길동", 2, 18, Arrays.asList("독서"));

        // when
        studentService.update(student);

        // then
        verify(studentRepository, times(1)).update(any());
    }

    @Test
    public void testDelete() {

        // given
        Long studentId = 1L;

        // when
        studentService.delete(studentId);

        // then
        verify(studentRepository, times(1)).delete(anyLong());
    }
}
