package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.ClassroomRepository;
import io.simpolor.jpa.repository.entity.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> saveAndGet(List<String> classroomNames) {

        List<Classroom> classrooms = classroomRepository.findAllByClassNameIn(classroomNames);
        if(CollectionUtils.isEmpty(classrooms)){
            for(String classroomName : classroomNames){
                classrooms.add(Classroom.builder().className(classroomName).build());
            }
            classroomRepository.saveAll(classrooms);
        }

        return classrooms;
    }

}
