package io.simpolor.h2.repository;

import io.simpolor.h2.domain.Student;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
// @ActiveProfiles("local")
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})*/
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testTotalcount(){

        // given


        // when
        long result = studentRepository.count();


        // than
        MatcherAssert.assertThat(2L, CoreMatchers.is(result));

        // print
        System.out.println("result :"+result);

    }

    @Test
    public void testFindAll(){

        // given
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "하니", 1, 17));
        students.add(new Student(2, "홍길동", 3, 19));


        // when
        List<Student> result = new ArrayList<>();
        studentRepository.findAll().iterator().forEachRemaining(result::add);


        // than
        MatcherAssert.assertThat(2, CoreMatchers.is(result.size()));
        assertIterableEquals(students, result);

        // print
        System.out.println("result :"+result);

    }

    @Test
    public void testFind(){

        // given
        long seq = 1L;
        Student student = new Student(seq, "하니", 1, 17);


        // when
        Optional<Student> result = studentRepository.findById(seq);


        // than
        MatcherAssert.assertThat(true, CoreMatchers.is(result.isPresent()));
        assertEquals( "하니", result.get().getName());
        assertEquals(student, result.get());

        // print
        System.out.println("result :"+result);

    }

    @Test
    public void testSave(){

        // given
        long seq = 3L;
        Student student = new Student(seq, "사나", 1, 17);


        // when
        Student result = studentRepository.save(student);


        // than
        assertEquals( "사나", result.getName());
        assertEquals(student, result);

        // print
        System.out.println("result :"+result);

    }

    @Test
    public void testDelete(){

        // given
        long seq = 1L;


        // when
        studentRepository.deleteById(seq);

        Optional<Student> result = studentRepository.findById(seq);


        // than
        MatcherAssert.assertThat(false, CoreMatchers.is(result.isPresent()));


        // print
        System.out.println("result :"+result);

    }
}
