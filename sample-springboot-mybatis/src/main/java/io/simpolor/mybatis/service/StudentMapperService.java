package io.simpolor.mybatis.service;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMapperService {

    @Autowired
    private StudentMapper studentMapper;

    public int getStudentTotalCount() {
        return studentMapper.selectStudentTotalCountMapper();
    }

    public List<Student> getStudentList() {
        return studentMapper.selectStudentListMapper();
    }

    public Student getStudent(long seq) {
        if(studentMapper.selectStudentMapper(seq) != null){
            return studentMapper.selectStudentMapper(seq);
        }
        return new Student();
    }

    public Student registerStudent(Student student) {
        if(studentMapper.insertStudentMapper(student) > 0){
            return student;
        }
        return new Student();
    }

    public Student modifyStudent(Student student) {
        if(studentMapper.updateStudentMapper(student) > 0){
            return student;
        }
        return new Student();
    }

    public long deleteStudent(long seq) {
        studentMapper.deleteStudentMapper(seq);
        return seq;
    }

}
