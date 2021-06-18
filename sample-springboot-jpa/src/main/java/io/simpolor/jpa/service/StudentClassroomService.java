package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentClassroomRepository;
import io.simpolor.jpa.repository.entity.StudentClassroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentClassroomService {

    private final StudentClassroomRepository studentClassroomRepository;

    public void create(List<StudentClassroom> studentClassrooms) {

        studentClassroomRepository.saveAll(studentClassrooms);
    }

    public void getAndDelete(Long studentSeq) {

        List<StudentClassroom> studentClassrooms = studentClassroomRepository.findAllByStudentSeq(studentSeq);
        if(!CollectionUtils.isEmpty(studentClassrooms)){
            studentClassroomRepository.deleteAll(studentClassrooms);
        }
    }

}
