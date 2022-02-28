package io.simpolor.mybatis.service;

import io.simpolor.mybatis.mapper.StudentMapper;
import io.simpolor.mybatis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentMapperService {

    private final StudentMapper studentMapper;

    public Long getTotalCount() {
        return studentMapper.selectStudentCountMapper();
    }

    public List<Student> getAll() {
        return studentMapper.selectAllStudentMapper();
    }

    public Student get(Long studentId) {

        Student student = studentMapper.selectStudentMapper(studentId);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return student;
    }

    public Student create(Student student) {

        studentMapper.insertStudentMapper(student);

        return student;
    }

    public void update(Student student) {

        if(Objects.isNull(studentMapper.selectStudentMapper(student.getStudentId()))){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        studentMapper.updateStudentMapper(student);
    }

    public void delete(Long studentId) {

        studentMapper.deleteStudentMapper(studentId);
    }

}
