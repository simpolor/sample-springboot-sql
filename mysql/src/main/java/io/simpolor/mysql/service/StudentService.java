package io.simpolor.mysql.service;

import io.simpolor.mysql.repository.StudentRepository;
import io.simpolor.mysql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    public Student register(Student student) {

        return studentRepository.save(student);
    }

    public void modify(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        studentRepository.save(student);
    }

    public void delete(Long studentId) {

        studentRepository.deleteById(studentId);
    }

}
