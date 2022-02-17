package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @Mock private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetAll() {

        // given
        Student student = Student.builder()
                .studentId(1L)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .favoriteFoods(Collections.EMPTY_SET)
                .studentTeachers(Collections.EMPTY_LIST)
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
        Long studentId = 1L;

        Student student = Student.builder()
                .studentId(studentId)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .favoriteFoods(Collections.EMPTY_SET)
                .studentTeachers(Collections.EMPTY_LIST)
                .build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        // when
        Student actual = studentService.get(studentId);

        // then
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(1, actual.getStudentId());

    }

    @Test
    public void testRegister() {

        // given
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .favoriteFoods(Collections.EMPTY_SET)
                .build();

        when(studentRepository.save(any())).thenReturn(student);

        // when
        studentService.create(student);

        // then
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void testModify() {

        // given
        Long studentId = 1L;
        Student student = Student.builder()
                .studentId(studentId)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .favoriteFoods(Collections.EMPTY_SET)
                .build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        // when
        // studentService.update(student, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        studentService.update(student);

        // then
        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void testDeleteById() {

        // given
        Long studentId = 1L;

        // when
        studentService.delete(studentId);

        // then
        verify(studentRepository, times(1)).deleteById(anyLong());
    }
}
