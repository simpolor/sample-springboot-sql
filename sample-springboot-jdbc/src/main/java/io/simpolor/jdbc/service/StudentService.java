package io.simpolor.jdbc.service;

import io.simpolor.jdbc.domain.Student;
import io.simpolor.jdbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getTotalCount() {

        return studentRepository.selectCount();
    }

    public List<Student> getAll() {

        return studentRepository.selectList();
    }

    public Student get(long seq) {

        Student student = studentRepository.selectOne(seq);

        if(student == null){
            new IllegalArgumentException("seq : "+seq);
        }

        return student;
    }

    public void register(Student student) {

        studentRepository.insert(student);
    }

    public void modify(Student student) {

        studentRepository.update(student);
    }

    public void delete(long seq) {

        studentRepository.delete(seq);
    }

}
