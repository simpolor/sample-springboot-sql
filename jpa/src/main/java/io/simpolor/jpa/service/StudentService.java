package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentRepository;
import io.simpolor.jpa.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ClassroomService classroomService;

    public long getTotalCount() {
        return studentRepository.count();
    }

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public Student get(long studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(!studentOptional.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return studentOptional.get();
    }

    public void create(Student student) {

        studentRepository.saveAndFlush(student);
    }

    public void update(Student student) {

        studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("studentId : "+student.getStudentId()));

        studentRepository.saveAndFlush(student);
    }

    public void delete(long studentId) {

        studentRepository.deleteById(studentId);
    }

}
