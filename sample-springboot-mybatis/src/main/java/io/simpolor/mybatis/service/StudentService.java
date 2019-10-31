package io.simpolor.mybatis.service;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public int getStudentTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public List<Student> getStudentList() {
        return studentRepository.selectStudentList();
    }

    public Student getStudent(long seq) {
        if(studentRepository.selectStudent(seq) != null){
            return studentRepository.selectStudent(seq);
        }
        return new Student();
    }

    public Student registerStudent(Student student) {
        if(studentRepository.insertStudent(student) > 0){
            return student;
        }
        return new Student();
    }

    public Student modifyStudent(Student student) {
        if(studentRepository.updateStudent(student) > 0){
            return student;
        }
        return new Student();
    }

    public long deleteStudent(long seq) {
        studentRepository.deleteStudent(seq);
        return seq;
    }

}
