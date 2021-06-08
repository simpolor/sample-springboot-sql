package io.simpolor.h2.service;

import io.simpolor.h2.repository.entity.Student;
import io.simpolor.h2.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        Iterable<Student> students = studentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student get(long seq) {

        return studentRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("seq : "+seq));
    }

    public Student create(Student student) {

        return studentRepository.save(student);
    }

    public Student update(Student student) {

        studentRepository.findById(student.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+student.getSeq()));

        return studentRepository.save(student);
    }

    public long delete(long seq) {

        studentRepository.deleteById(seq);

        return seq;
    }

}
