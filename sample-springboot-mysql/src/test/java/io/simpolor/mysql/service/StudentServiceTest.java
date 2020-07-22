package io.simpolor.mysql.service;

import io.simpolor.mysql.domain.Student;
import io.simpolor.mysql.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StudentService.class})
public class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    public void testGetStudentTotalCount(){

        // given
        when(studentRepository.count()).thenReturn(3L);

        // when
        long result = studentService.getStudentTotalCount();

        // then
        Assertions.assertThat(result)
                .isEqualTo(3);

    }

    @Test
    public void testGetStudentList(){

        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, "단순색", 1, 17, Arrays.asList("축구")));
        students.add(new Student(2L, "김영희", 2, 18, Arrays.asList("영화", "프로그래밍")));

        when(studentRepository.findAll()).thenReturn(students);

        // when
        List<Student> result = studentService.getStudentList();

        // then
        Assertions.assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .first()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testGetStudent(){

        // given
        long seq = 1;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentRepository.selectStudent(seq)).thenReturn(student);

        // when
        Student result = studentService.getStudent(seq);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testRegisterStudent(){

        // given
        long seq = 1;
        Student student = new Student(0, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentRepository.save(student)).thenReturn(student);

        // when
        Student result = studentService.registerStudent(student);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testModifyStudent(){

        // given
        long seq = 1;
        Student student = new Student(seq, "단순색", 1, 17, Arrays.asList("축구"));

        when(studentRepository.save(student)).thenReturn(student);

        // when
        Student result = studentService.modifyStudent(student);

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .extracting(Student::getName)
                .isEqualTo("단순색");
    }

    @Test
    public void testDeleteStudent(){

        // given
        long seq = 1;

        // when
        long result = studentService.deleteStudent(seq);

        // then
        Assertions.assertThat(result)
                .isEqualTo(1);
    }

}
