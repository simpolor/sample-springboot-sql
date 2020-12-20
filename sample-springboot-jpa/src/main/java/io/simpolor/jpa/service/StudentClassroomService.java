package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.StudentClassroomRepository;
import io.simpolor.jpa.repository.entity.StudentClassroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassroomService {

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    public void register(List<StudentClassroom> studentClassrooms) {

        studentClassroomRepository.saveAll(studentClassrooms);
    }

}
