package io.simpolor.h2.repository;

import io.simpolor.h2.H2Application;
import io.simpolor.h2.domain.Student;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {H2Application.class})
public class StudentRepositoryIntergrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testTotalcount(){

        // given, when
        long result = studentRepository.count();

        // than
        MatcherAssert.assertThat(2L, CoreMatchers.is(result));
    }

    @Test
    public void testFindAll(){

        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "하니", 1, 17));
        students.add(new Student(2, "홍길동", 3, 19));

        // when
        List<Student> actual = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(actual::add);

        // than
        MatcherAssert.assertThat(2, CoreMatchers.is(actual.size()));
        assertIterableEquals(students, actual);
    }

    @Test
    public void testFind(){

        // given
        long seq = 1L;
        Student student = new Student(seq, "하니", 1, 17);

        // when
        Optional<Student> actual = studentRepository.findById(seq);

        // than
        MatcherAssert.assertThat(true, CoreMatchers.is(actual.isPresent()));
        assertEquals( "하니", actual.get().getName());
        assertEquals(student, actual.get());
    }

    @Test
    public void testSave(){

        // given
        long seq = 3L;
        Student student = new Student(seq, "사나", 1, 17);

        // when
        Student actual = studentRepository.save(student);

        // than
        assertEquals( "사나", actual.getName());
        assertEquals(student, actual);
    }

    @Test
    public void testDelete(){

        // given
        long seq = 1L;

        // when
        studentRepository.deleteById(seq);
        Optional<Student> actual = studentRepository.findById(seq);

        // than
        MatcherAssert.assertThat(false, CoreMatchers.is(actual.isPresent()));
    }
}
