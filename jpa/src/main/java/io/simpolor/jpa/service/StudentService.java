package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.StudentTeacherRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.StudentTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(!studentOptional.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return studentOptional.get();
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public void update(Student student) {

        studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("studentId : "+student.getStudentId()));

        studentRepository.save(student);
    }

    public void delete(long studentId) {

        studentRepository.deleteById(studentId);
    }

}
