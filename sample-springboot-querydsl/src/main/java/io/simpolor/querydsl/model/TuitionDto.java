package io.simpolor.querydsl.model;

import io.simpolor.querydsl.repository.entity.Tuition;
import io.simpolor.querydsl.repository.entity.Student;
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

    private Long seq;
    private Long studentSeq;
    private String name;
    private Long fees;

    public Tuition toEntity(){

        return Tuition.builder()
                .seq(this.seq)
                .student(Student.builder().seq(studentSeq).build())
                .fees(this.fees)
                .build();
    }

    public static TuitionDto of(Tuition tuition){

        return TuitionDto.builder()
                .seq(tuition.getSeq())
                .studentSeq(Objects.nonNull(tuition.getStudent()) ? tuition.getStudent().getSeq(): null)
                .name(Objects.nonNull(tuition.getStudent()) ? tuition.getStudent().getName(): null)
                .fees(tuition.getFees())
                .build();
    }

    public static List<TuitionDto> of(List<Tuition> Expenses){

        return Expenses.stream()
                .map(TuitionDto::of)
                .collect(Collectors.toList());
    }

}
