package io.simpolor.h2.service;

import io.simpolor.h2.domain.Student;
import io.simpolor.h2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getStudentTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getStudentList() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student getStudent(long seq) {
        return studentRepository.findById(seq).orElse(new Student());
    }

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student modifyStudent(Student student) {
        if(studentRepository.findById(student.getSeq()).isPresent()){
            return studentRepository.save(student);
        }
        return new Student();
    }

    public long deleteStudent(long seq) {
        studentRepository.deleteById(seq);
        return seq;
    }

}
