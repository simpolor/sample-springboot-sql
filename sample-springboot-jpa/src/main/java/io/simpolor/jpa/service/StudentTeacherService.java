package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentTeacherRepository;
import io.simpolor.jpa.repository.entity.StudentTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentTeacherService {

    @Autowired
    private StudentTeacherRepository studentTeacherRepository;

    public void register(List<StudentTeacher> studentTeachers) {

        studentTeacherRepository.saveAll(studentTeachers);
    }

    public void modify(List<StudentTeacher> studentTeachers, Long studentSeq) {

        List<StudentTeacher> saveBulk = new ArrayList<>();
        List<StudentTeacher> deleteBulk = new ArrayList<>();

        List<StudentTeacher> orgStudentTeachers = studentTeacherRepository.findAllByStudentSeq(studentSeq);
        for(StudentTeacher s1 : studentTeachers){
            for(StudentTeacher s2 : orgStudentTeachers){
                if(s1.getSeq() != s2.getSeq()){
                    // if(s2.getSeq() == 0L) saveBulk.add(s2);
                }
            }
        }

        studentTeacherRepository.saveAll(studentTeachers);
    }

}
