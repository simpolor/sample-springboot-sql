package io.simpolor.querydsl.model;

import io.simpolor.querydsl.repository.group.TuitionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTuitionDto {

    private Long studentSeq;
    private String name;
    private Long fees;

    public static StudentTuitionDto of(TuitionGroup tuitionGroup){

        return StudentTuitionDto.builder()
                .studentSeq(tuitionGroup.getStudentSeq())
                .name(tuitionGroup.getName())
                .fees(tuitionGroup.getFees())
                .build();
    }

    public static List<StudentTuitionDto> of(List<TuitionGroup> tuitions){

        return tuitions.stream()
                .map(StudentTuitionDto::of)
                .collect(Collectors.toList());
    }

}
