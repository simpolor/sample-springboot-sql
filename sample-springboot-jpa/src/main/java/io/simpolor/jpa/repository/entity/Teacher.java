package io.simpolor.jpa.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String teacherName;

    public Teacher(String teacherName){
        this.teacherName = teacherName;
    }

    public static List<Teacher> of(List<String> teacherNames){

        return teacherNames.stream().map(t -> new Teacher(t)).collect(Collectors.toList());
    }
}
