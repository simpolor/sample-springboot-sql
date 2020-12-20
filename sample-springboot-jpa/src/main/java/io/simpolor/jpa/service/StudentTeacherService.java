package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentTeacherRepository;
import io.simpolor.jpa.repository.entity.StudentTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTeacherService {

    @Autowired
    private StudentTeacherRepository studentTeacherRepository;

    public void register(List<StudentTeacher> studentTeachers) {

        studentTeacherRepository.saveAll(studentTeachers);
    }

}
