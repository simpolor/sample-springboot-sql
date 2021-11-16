package io.simpolor.h2.service;

import io.simpolor.h2.repository.StudentRepository;
import io.simpolor.h2.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student get(long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Student update(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return studentRepository.save(student);
    }

    public long delete(long studentId) {

        studentRepository.deleteById(studentId);

        return studentId;
    }

}
