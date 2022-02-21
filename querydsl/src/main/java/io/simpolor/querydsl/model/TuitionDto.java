package io.simpolor.querydsl.model;

import io.simpolor.querydsl.repository.entity.Student;
import io.simpolor.querydsl.repository.entity.Tuition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TuitionDto {

    private Long id;
    private Long studentId;
    private String name;
    private Long fees;

    public Tuition toEntity(){

        return Tuition.builder()
                .tuitionId(this.id)
                .student(Student.builder()
                        .studentId(this.studentId)
                        .build())
                .fees(this.fees)
                .build();
    }

    public static TuitionDto of(Tuition tuition){

        return TuitionDto.builder()
                .id(tuition.getTuitionId())
                .studentId(Objects.nonNull(tuition.getStudent()) ? tuition.getStudent().getStudentId(): null)
                .name(Objects.nonNull(tuition.getStudent()) ? tuition.getStudent().getName(): null)
                .fees(tuition.getFees())
                .build();
    }

    public static List<TuitionDto> of(List<Tuition> tuitions){

        return tuitions.stream()
                .map(TuitionDto::of)
                .collect(Collectors.toList());
    }

}
