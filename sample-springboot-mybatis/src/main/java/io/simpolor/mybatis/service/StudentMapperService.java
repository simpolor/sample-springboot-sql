package io.simpolor.mybatis.service;

import io.simpolor.mybatis.domain.Student;
import io.simpolor.mybatis.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentMapperService {

    @Autowired
    private StudentMapper studentMapper;

    public long getTotalCount() {
        return studentMapper.selectStudentTotalCountMapper();
    }

    public List<Student> getAll() {
        return studentMapper.selectStudentListMapper();
    }

    public Student get(long seq) {
        Student student = studentMapper.selectStudentMapper(seq);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("seq : "+seq);
        }
        return student;
    }

    public void register(Student student) {
        studentMapper.insertStudentMapper(student);
    }

    public void modify(Student student) {
        if(Objects.isNull(studentMapper.selectStudentMapper(student.getSeq()))){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        studentMapper.updateStudentMapper(student);
    }

    public void delete(long seq) {
        studentMapper.deleteStudentMapper(seq);
    }

}
