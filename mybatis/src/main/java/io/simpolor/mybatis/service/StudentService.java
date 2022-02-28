package io.simpolor.mybatis.service;

import io.simpolor.mybatis.repository.StudentRepository;
import io.simpolor.mybatis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Long getTotalCount() {
        return studentRepository.selectStudentCount();
    }

    public List<Student> getAll() {
        return studentRepository.selectAllStudent();
    }

    public Student get(Long studentId) {

        Student student = studentRepository.selectStudent(studentId);
        if(Objects.isNull(student)){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return student;
    }

    public Long create(Student student) {

        studentRepository.insertStudent(student);

        return student.getStudentId();
    }

    public void update(Student student) {

        if(Objects.isNull(studentRepository.selectStudent(student.getStudentId()))){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }
        studentRepository.updateStudent(student);
    }

    public void delete(Long studentId) {

        studentRepository.deleteStudent(studentId);
    }

}
