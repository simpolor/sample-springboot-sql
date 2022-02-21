package io.simpolor.querydsl.repository.group;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TuitionGroup {

    private Long studentSeq;
    private String name;
    private Long fees;

    public TuitionGroup(Long studentSeq, String name, Long fees){
        this.studentSeq = studentSeq;
        this.name = name;
        this.fees = fees;
    }
}
