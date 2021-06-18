package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.ClassroomRepository;
import io.simpolor.jpa.repository.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public List<Classroom> saveAndGet(List<String> classroomNames) {

        List<Classroom> classrooms = classroomRepository.findAllByClassNameIn(classroomNames);
        List<String> orgClassroomNames = classrooms.stream().map(Classroom::getClassName).collect(Collectors.toList());

        for(String classroomName : classroomNames){
            if(!orgClassroomNames.contains(classroomName)){
                classrooms.add(Classroom.builder().className(classroomName).build());
            }
        }
        classroomRepository.saveAll(classrooms);

        return classrooms;
    }

}
