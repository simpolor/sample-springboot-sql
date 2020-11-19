package io.simpolor.h2.service;

import io.simpolor.h2.domain.Student;
import io.simpolor.h2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getTotalCount() {
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

    public Student register(Student request) {

        return studentRepository.save(request);
    }

    public Student modify(Student request) {

        Student student = studentRepository.findById(request.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+request.getSeq()));

        return studentRepository.save(student);
    }

    public long delete(long seq) {

        studentRepository.deleteById(seq);

        return seq;
    }

}
