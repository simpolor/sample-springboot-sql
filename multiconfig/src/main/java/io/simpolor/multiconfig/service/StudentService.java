package io.simpolor.multiconfig.service;

import io.simpolor.multiconfig.repository.primary.StudentRepository;
import io.simpolor.multiconfig.repository.entity.Student;
import io.simpolor.multiconfig.repository.secondary.SeStudentRepository;
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
    private final SeStudentRepository seStudentRepository;

    public List<Student> getAll() {

        Iterable<Student> students = seStudentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student get(Long studentId) {

        Optional<Student> optionalStudent = seStudentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    public Student getName(String name) {

        Optional<Student> optionalStudent = seStudentRepository.findByName(name);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("name : "+name);
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

    public Long delete(Long studentId) {

        studentRepository.deleteById(studentId);

        return studentId;
    }

}
