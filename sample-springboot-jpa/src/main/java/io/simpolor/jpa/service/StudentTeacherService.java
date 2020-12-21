package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentTeacherRepository;
import io.simpolor.jpa.repository.entity.StudentTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentTeacherService {

    @Autowired
    private StudentTeacherRepository studentTeacherRepository;

    public void register(List<StudentTeacher> studentTeachers) {

        studentTeacherRepository.saveAll(studentTeachers);
    }

    @Transactional
    public void modify(List<StudentTeacher> studentTeachers, Long studentSeq) {

        List<StudentTeacher> saveBulk = new ArrayList<>();
        List<StudentTeacher> deleteBulk = new ArrayList<>();

        List<StudentTeacher> orgStudentTeachers = studentTeacherRepository.findAllByStudentSeq(studentSeq);
        List<Long> orgSecs = orgStudentTeachers.stream().map(StudentTeacher::getSeq).collect(Collectors.toList());
        List<Long> secs = studentTeachers.stream().map(StudentTeacher::getSeq).collect(Collectors.toList());

        for(StudentTeacher studentTeacher : studentTeachers){
            if(!orgSecs.contains(studentTeacher.getSeq())){
                saveBulk.add(studentTeacher);
            }
        }

        for(StudentTeacher studentTeacher : orgStudentTeachers){
            if(!secs.contains(studentTeacher.getSeq())){
                deleteBulk.add(studentTeacher);
            }
        }

        studentTeacherRepository.saveAll(saveBulk);
        studentTeacherRepository.saveAll(deleteBulk);
    }

}
