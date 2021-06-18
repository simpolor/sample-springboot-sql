package io.simpolor.jdbc.service;

import io.simpolor.jdbc.model.StudentDto;
import io.simpolor.jdbc.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public long getTotalCount() {

        return studentRepository.selectCount();
    }

    public List<StudentDto> getAll() {

        return studentRepository.selectList();
    }

    public StudentDto get(long seq) {

        StudentDto student = studentRepository.selectOne(seq);

        if(student == null){
            new IllegalArgumentException("seq : "+seq);
        }

        return student;
    }

    public void register(StudentDto student) {

        studentRepository.insert(student);
    }

    public void modify(StudentDto student) {

        studentRepository.update(student);
    }

    public void delete(long seq) {

        studentRepository.delete(seq);
    }

}
