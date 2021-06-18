package io.simpolor.mysql.service;

import io.simpolor.mysql.repository.StudentRepository;
import io.simpolor.mysql.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long seq) {

        Optional<Student> optionalStudent = studentRepository.findById(seq);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudent.get();
    }

    public void register(Student student) {

        studentRepository.save(student);
    }

    public void modify(Student student) {

        Optional<Student> optionalStudent = studentRepository.findById(student.getSeq());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+student.getSeq());
        }

        studentRepository.save(student);
    }

    public void delete(long seq) {

        studentRepository.deleteById(seq);
    }

}
