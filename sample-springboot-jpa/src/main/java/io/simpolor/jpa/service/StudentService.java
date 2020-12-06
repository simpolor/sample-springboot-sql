package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long seq) {

        return studentRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("seq : "+seq));
    }

    public void register(Student student) {

        /*List<Long> teachers = teachService.saveAndGet(student.getTeachers());
        System.out.println("teachers : "+teachers);*/

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
