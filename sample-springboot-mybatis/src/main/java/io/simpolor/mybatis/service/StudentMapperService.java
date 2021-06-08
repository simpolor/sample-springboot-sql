package io.simpolor.mybatis.service;

import io.simpolor.mybatis.mapper.StudentMapper;
import io.simpolor.mybatis.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentMapperService {

    private final StudentMapper studentMapper;

    public long getTotalCount() {
        return studentMapper.selectStudentTotalCountMapper();
    }

    public List<StudentDto> getAll() {
        return studentMapper.selectStudentListMapper();
    }

    public StudentDto get(long seq) {
        StudentDto student = studentMapper.selectStudentMapper(seq);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("seq : "+seq);
        }
        return student;
    }

    public void create(StudentDto student) {
        studentMapper.insertStudentMapper(student);
    }

    public void update(StudentDto student) {
        if(Objects.isNull(studentMapper.selectStudentMapper(student.getSeq()))){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        studentMapper.updateStudentMapper(student);
    }

    public void delete(long seq) {
        studentMapper.deleteStudentMapper(seq);
    }

}
