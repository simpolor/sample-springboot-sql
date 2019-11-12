package io.simpolor.h2.service;


import io.simpolor.h2.domain.Student;
import io.simpolor.h2.repository.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

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
    public void testStuentTotalcount() {

        // given
        long reuturnValue = 3L;
        when(studentRepository.count()).thenReturn(reuturnValue);


        // when
        long result = studentService.getStudentTotalCount();


        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3L, result);
    }

    @Test
    public void testStudentList() {

        // given
        Student student = new Student(1, "홍길동", 2, 18);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student));


        // when
        List<Student> result = studentService.getStudentList();


        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());

    }

    @Test
    public void testStudentView() {

        // given
        long seq = 1;

        Student student = new Student(1, "홍길동", 2, 18);
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));


        // when
        Student result = studentService.getStudent(seq);


        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getSeq());

    }

    @Test
    public void testStudentSave() {

        // given
        long seq = 1;

        Student student = new Student(0, "홍길동", 2, 18);
        Student response = new Student(seq, "홍길동", 2, 18);

        when(studentRepository.save(student)).thenReturn(response);

        // when
        Student result = studentService.registerStudent(student);


        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(seq, result.getSeq());

    }

    @Test
    public void testStudentModify() {

        // given
        long seq = 1;

        Student student = new Student(seq, "홍길동", 2, 18);
        Student response = new Student(seq, "홍길순", 2, 18);

        when(studentRepository.findById(seq)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(response);


        // when
        Student result = studentService.modifyStudent(student);


        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(seq, result.getSeq());
        Assertions.assertEquals("홍길순", result.getName());

    }
}
