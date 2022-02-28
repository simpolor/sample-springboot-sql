package io.simpolor.querydsl.service;

import com.querydsl.core.QueryResults;
import io.simpolor.querydsl.repository.StudentRepository;
import io.simpolor.querydsl.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public QueryResults<Student> search(Student search) {

        return studentRepository.search(search);
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(Long studentId) {

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

    public void delete(Long studentId) {

        studentRepository.deleteById(studentId);
    }

}
