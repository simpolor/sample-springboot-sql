package io.simpolor.querydsl.service;

import com.querydsl.core.QueryResults;
import io.simpolor.querydsl.repository.StudentRepository;
import io.simpolor.querydsl.repository.entity.Student;
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

    public QueryResults<Student> getAllByName(String name) {
        return studentRepository.findAllBySearch(name);
    }

    public Student get(long seq) {

        Optional<Student> studentOptional = studentRepository.findById(seq);
        if(!studentOptional.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return studentOptional.get();
    }

    public void register(Student student) {

        studentRepository.save(student);
    }

    public void modify(Student student) {

        studentRepository.findById(student.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+student.getSeq()));

        studentRepository.save(student);
    }

    public void delete(long seq) {

        studentRepository.deleteById(seq);
    }

}
