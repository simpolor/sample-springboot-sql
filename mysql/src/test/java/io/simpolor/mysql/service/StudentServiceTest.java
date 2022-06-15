package io.simpolor.mysql.service;

import io.simpolor.mysql.repository.StudentRepository;
import io.simpolor.mysql.repository.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Student student = Student.builder()
                .studentId(1L)
                .name("홍길동")
                .grade(1)
                .age(17)
                .build();

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
        Long id = 1L;

        Student student = Student.builder()
                .studentId(id)
                .name("홍길동")
                .grade(1)
                .age(17)
                .build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        // when
        Student actual = studentService.get(id);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getStudentId());

    }

    @Test
    public void testCreate() {

        // given
        Long id = 1L;
        Student student = Student.builder()
                .studentId(id)
                .name("홍길동")
                .grade(1)
                .age(17)
                .build();

        when(studentRepository.save(any())).thenReturn(student);

        // when
        studentService.create(student);

        // then
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void testUpdate() {

        // given
        Long id = 1L;
        Student student = Student.builder()
                .studentId(id)
                .name("홍길동")
                .grade(1)
                .age(17)
                .build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        // when
        studentService.update(student);

        // then
        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void testDelete() {

        // given
        Long id = 1L;

        // when
        studentService.delete(id);

        // then
        verify(studentRepository, times(1)).deleteById(anyLong());
    }
}
