package io.simpolor.masterslave.service;

import io.simpolor.masterslave.repository.StudentRepository;
import io.simpolor.masterslave.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getAll() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    @Transactional(readOnly = true)
    public Student get(Long studentId) {

        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    @Transactional
    public Student create(Student student) {

        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return studentRepository.save(student);
    }

    @Transactional
    public Long delete(Long studentId) {

        studentRepository.deleteById(studentId);

        return studentId;
    }

}
