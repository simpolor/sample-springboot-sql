package io.simpolor.mybatis.service;

import io.simpolor.mybatis.model.StudentDto;
import io.simpolor.mybatis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public long getTotalCount() {
        return studentRepository.selectStudentTotalCount();
    }

    public List<StudentDto> getAll() {
        return studentRepository.selectStudentList();
    }

    public StudentDto get(long seq) {
        StudentDto student = studentRepository.selectStudent(seq);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        return student;
    }

    public void create(StudentDto student) {
        studentRepository.insertStudent(student);
    }

    public void update(StudentDto student) {
        if(Objects.isNull(studentRepository.selectStudent(student.getSeq()))){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }
        studentRepository.updateStudent(student);
    }

    public void delete(long seq) {
        studentRepository.deleteStudent(seq);
    }

}
