package io.simpolor.jdbc.service;

import io.simpolor.jdbc.domain.Student;
import io.simpolor.jdbc.repository.StudentRepository;
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
        long result = studentRepository.insertStudent(student);
        if(result > 0){
            student.setSeq(result);
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
