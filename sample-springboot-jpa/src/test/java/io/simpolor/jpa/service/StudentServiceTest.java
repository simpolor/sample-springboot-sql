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
    @Mock private TagService tagService;
    @Mock private ClassroomService classroomService;
    @Mock private StudentClassroomService studentClassroomService;
    @Mock private TeacherService teacherService;
    @Mock private StudentTeacherService studentTeacherService;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testTotalCount() {

        // given
        long totalCount = 3L;
        when(studentRepository.count()).thenReturn(totalCount);

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
                .seq(1L)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .pets(Collections.EMPTY_LIST)
                .studentClassrooms(Collections.EMPTY_LIST)
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
        long seq = 1;

        Student student = Student.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .pets(Collections.EMPTY_LIST)
                .studentClassrooms(Collections.EMPTY_LIST)
                .studentTeachers(Collections.EMPTY_LIST)
                .build();

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
        Student student = Student.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .pets(Collections.EMPTY_LIST)
                .build();

        when(studentRepository.save(any())).thenReturn(student);

        // when
        studentService.create(student, Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        // then
        verify(studentRepository, times(1)).saveAndFlush(any());
        verify(tagService, times(0)).create(any(), any());
        verify(classroomService, times(1)).saveAndGet(any());
        verify(studentClassroomService, times(0)).create(any());
        verify(teacherService, times(1)).getAll(any());
        verify(studentTeacherService, times(0)).create(any());
    }

    @Test
    public void testModify() {

        // given
        long seq = 1;
        Student student = Student.builder()
                .seq(seq)
                .name("하니")
                .grade(1)
                .age(17)
                .hobbies(Arrays.asList("달리기"))
                .pets(Collections.EMPTY_LIST)
                .build();

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any())).thenReturn(student);

        // when
        // studentService.update(student, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        studentService.update(student);

        // then
        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentRepository, times(1)).saveAndFlush(any());
        verify(studentClassroomService, times(1)).getAndDelete(any());
        verify(classroomService, times(1)).saveAndGet(any());
        verify(studentClassroomService, times(0)).create(any());
        verify(teacherService, times(1)).getAll(any());
        verify(studentTeacherService, times(0)).update(any(), anyLong());
    }

    @Test
    public void testDeleteById() {

        // given
        long seq = 1;

        // when
        studentService.delete(seq);

        // then
        verify(studentClassroomService, times(1)).getAndDelete(anyLong());
        verify(studentTeacherService, times(1)).getAndDelete(anyLong());
        verify(studentRepository, times(1)).deleteById(anyLong());
    }
}
