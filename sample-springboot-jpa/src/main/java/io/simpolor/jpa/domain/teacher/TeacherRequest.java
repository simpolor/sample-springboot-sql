package io.simpolor.jpa.domain.teacher;

import io.simpolor.jpa.repository.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequest {

    private long seq;
    private String name;

    public Teacher toTeacher(){

        return Teacher.builder()
                .teacherName(this.name)
                .build();
    }

    public Teacher toTeacher(long seq){

        return Teacher.builder()
                .seq(seq)
                .teacherName(this.name)
                .build();
    }
}
