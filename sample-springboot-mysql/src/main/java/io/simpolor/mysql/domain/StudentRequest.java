package io.simpolor.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {

    private long seq;
    private String name;
    private int grade;
    private int age;

    private List<String> hobbies = new ArrayList<>();
    private Set<String> foodNames = new HashSet<>();
    private List<String> classRooms = new ArrayList<>();

    public Student toStudent(){

        Student student = new Student();
        student.setName(name);
        student.setGrade(grade);
        student.setAge(age);
        student.setHobby(toHobby(hobbies));
        student.setFoodNames(foodNames);

        return student;
    }

    public Student toStudent(long seq){

        Student student = new Student();
        student.setSeq(seq);
        student.setName(name);
        student.setGrade(grade);
        student.setAge(age);
        student.setHobby(toHobby(hobbies));
        student.setFoodNames(foodNames);

        return student;
    }

    private List<Hobby> toHobby(List<String> hobbies){
        List<Hobby> list = new ArrayList<>();
        for(String s : hobbies){
            Hobby hobby = new Hobby();
            hobby.setHobby(s);
            list.add(hobby);
        }
        return list;
    }
}
