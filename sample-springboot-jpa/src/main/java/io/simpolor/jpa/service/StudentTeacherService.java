package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentTeacherRepository;
import io.simpolor.jpa.repository.entity.StudentTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentTeacherService {

    private final StudentTeacherRepository studentTeacherRepository;

    public void create(List<StudentTeacher> studentTeachers) {

        studentTeacherRepository.saveAll(studentTeachers);
    }

    @Transactional
    public void update(List<StudentTeacher> studentTeachers, Long studentSeq) {

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

    public void getAndDelete(Long studentSeq) {

        List<StudentTeacher> studentTeachers = studentTeacherRepository.findAllByStudentSeq(studentSeq);
        if(!CollectionUtils.isEmpty(studentTeachers)){
            studentTeacherRepository.deleteAll(studentTeachers);
        }
    }

}
