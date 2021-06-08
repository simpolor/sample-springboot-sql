package io.simpolor.mybatis.service;

import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.repository.StudentRepository;
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
        when(studentRepository.selectStudentTotalCount()).thenReturn(reuturnValue);

        // when
        long actual = studentService.getTotalCount();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(3L, actual);
    }

    @Test
    public void testGetAll() {

        // given
        StudentDto student = StudentDto.builder()
                .seq(1)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("반항"))
                .build();

        when(studentRepository.selectStudentList()).thenReturn(Arrays.asList(student));

        // when
        List<StudentDto> actual = studentService.getAll();

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.size());

    }

    @Test
    public void testGet() {

        // given
        long seq = 1;

        StudentDto student = StudentDto.builder()
                .seq(1)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("반항"))
                .build();

        when(studentRepository.selectStudent(anyLong())).thenReturn(student);

        // when
        StudentDto actual = studentService.get(seq);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getSeq());

    }

    @Test
    public void testCreate() {

        // given
        long seq = 1;
        StudentDto student = StudentDto.builder()
                .seq(seq)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("반항"))
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
        long seq = 1;
        StudentDto student = StudentDto.builder()
                .seq(seq)
                .name("홍길동")
                .grade(1)
                .age(17)
                .hobby(Arrays.asList("공부"))
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
        long seq = 1;

        // when
        studentService.delete(seq);

        // then
        verify(studentRepository, times(1)).deleteStudent(anyLong());
    }
}
