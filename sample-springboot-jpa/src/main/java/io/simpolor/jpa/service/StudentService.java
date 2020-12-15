package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TagService tagService;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long seq) {

        Optional<Student> studentOptional = studentRepository.findById(seq);
        if(!studentOptional.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        /*Tag tag = tagService.get(1L);
        System.out.println("tag : "+tag);*/

        return studentOptional.get();
    }

    public void register(Student student) {

        /*List<Long> teachers = teachService.saveAndGet(student.getTeachers());
        System.out.println("teachers : "+teachers);*/

        studentRepository.saveAndFlush(student);
        if(Objects.nonNull(student.getTag())) {
            tagService.register(student.getTag().getTitle(), student);
        }

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
