package io.simpolor.h2.service;

import io.simpolor.h2.repository.entity.Student;
import io.simpolor.h2.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
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
    public void testGetTotalCount() {

        // given
        long reuturnValue = 3L;
        when(studentRepository.count()).thenReturn(reuturnValue);

        // when
        long actual = studentService.getTotalCount();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(3L, actual);
    }

    @Test
    public void testGetAll() {

        // given
        Student student = new Student(1, "홍길동", 2, 18);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));

        // when
        List<Student> actual = studentService.getAll();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());

    }

    @Test
    public void testGet() {

        // given
        long seq = 1;

        Student student = new Student(1, "홍길동", 2, 18);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        // when
        Student actual = studentService.get(seq);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getSeq());

    }

    @Test
    public void testRegister() {

        // given
        long seq = 1;

        Student student = new Student(0, "홍길동", 2, 18);
        Student response = new Student(seq, "홍길동", 2, 18);

        when(studentRepository.save(student)).thenReturn(response);

        // when
        Student actual = studentService.register(student);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(seq, actual.getSeq());

    }

    @Test
    public void testModify() {

        // given
        long seq = 1;

        Student student = new Student(seq, "홍길동", 2, 18);
        Student response = new Student(seq, "홍길순", 2, 18);

        when(studentRepository.findById(seq)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(response);

        // when
        Student actual = studentService.modify(student);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(seq, actual.getSeq());
        Assertions.assertEquals("홍길순", actual.getName());

    }
}
