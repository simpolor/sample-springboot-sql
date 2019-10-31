package io.simpolor.jpa.service;

import io.simpolor.jpa.domain.Student;
import io.simpolor.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getStudentTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    public Student getStudent(long seq) {
        // return studentRepository.findById(seq).orElse(new Student());
        return studentRepository.selectStudent(seq);
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
