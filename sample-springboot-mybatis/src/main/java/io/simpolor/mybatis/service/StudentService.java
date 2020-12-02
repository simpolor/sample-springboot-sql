package io.simpolor.mybatis.service;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public List<Student> getAll() {
        return studentRepository.selectStudentList();
    }

    public Student get(long seq) {
        Student student = studentRepository.selectStudent(seq);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        return student;
    }

    public void register(Student student) {
        studentRepository.insertStudent(student);
    }

    public void modify(Student student) {
        if(Objects.isNull(studentRepository.selectStudent(student.getSeq()))){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        studentRepository.updateStudent(student);
    }

    public void delete(long seq) {
        studentRepository.deleteStudent(seq);
    }

}
