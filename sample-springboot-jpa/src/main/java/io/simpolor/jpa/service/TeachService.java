package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.TeacherRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TeachService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Long> saveAndGet(List<Teacher> teachers) {

        List<String> teacherNames = teachers.stream().map(Teacher::getTeacherName).collect(Collectors.toList());

        List<Teacher> resultData = teacherRepository.findAllByTeacherNameIn(teacherNames);
        System.out.println("resultData : "+resultData);
        Map<String, Teacher> resultDataMap = resultData.stream().collect(Collectors.toMap(Teacher::getTeacherName, Function.identity()));
        System.out.println("resultDataMap : "+resultDataMap);

        List<Teacher> insertData = new ArrayList<>();
        for(String teacherName : teacherNames){
            if(Objects.isNull(resultDataMap.get(teacherName))){
                insertData.add(new Teacher(teacherName));
            }
        }
        System.out.println("insertData : "+insertData);

        // List<Teacher> savedData = teacherRepository.saveAll(insertData);

        List<Long> returnData = new ArrayList<>();
        // returnData.addAll(savedData.stream().map(Teacher::getSeq).collect(Collectors.toList()));
        // returnData.addAll(resultData.stream().map(Teacher::getSeq).collect(Collectors.toList()));

        return returnData;
    }

}
