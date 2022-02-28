package io.simpolor.jdbc.service;

import io.simpolor.jdbc.repository.StudentRepository;
import io.simpolor.jdbc.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {

        return studentRepository.selectList();
    }

    public Student get(Long studentId) {

        Student student = studentRepository.selectOne(studentId);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return student;
    }

    public Student create(Student student) {

        return studentRepository.insert(student);
    }

    public void update(Student student) {

        studentRepository.update(student);
    }

    public void delete(Long studentId) {

        studentRepository.delete(studentId);
    }

}
