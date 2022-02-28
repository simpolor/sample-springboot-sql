package io.simpolor.mybatis.service;

import io.simpolor.mybatis.repository.StudentRepository;
import io.simpolor.mybatis.repository.entity.Student;
import org.junit.jupiter.api.Assertions;
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
    public void testGetTotalCount() {

        // given
        long reuturnValue = 3L;
        when(studentRepository.selectStudentCount()).thenReturn(reuturnValue);

        // when
        long actual = studentService.getTotalCount();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(3L, actual);
    }

    @Test
    public void testGetAll() {

        // given
        Student student = Student.builder()
                .studentId(1L)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("반항"))
                .build();

        when(studentRepository.selectAllStudent()).thenReturn(Arrays.asList(student));

        // when
        List<Student> actual = studentService.getAll();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());

    }

    @Test
    public void testGet() {

        // given
        Long studentId = 1L;

        Student student = Student.builder()
                .studentId(studentId)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("반항"))
                .build();

        when(studentRepository.selectStudent(anyLong())).thenReturn(student);

        // when
        Student actual = studentService.get(studentId);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getStudentId());

    }

    @Test
    public void testCreate() {

        // given
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("반항"))
                .build();

        when(studentRepository.insertStudent(any())).thenReturn(1);

        // when
        studentService.create(student);

        // then
        verify(studentRepository, times(1)).insertStudent(any());
    }

    @Test
    public void testUpdate() {

        // given
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("공부"))
                .build();

        when(studentRepository.selectStudent(anyLong())).thenReturn(student);
        when(studentRepository.updateStudent(any())).thenReturn(1);

        // when
        studentService.update(student);

        // then
        verify(studentRepository, times(1)).selectStudent(anyLong());
        verify(studentRepository, times(1)).updateStudent(any());
    }

    @Test
    public void testDelete() {

        // given
        Long studentId = 1L;

        // when
        studentService.delete(studentId);

        // then
        verify(studentRepository, times(1)).deleteStudent(anyLong());
    }
}
