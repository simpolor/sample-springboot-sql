package io.simpolor.querydsl.repository.querydsl;

import io.simpolor.querydsl.repository.entity.Tuition;
import io.simpolor.querydsl.repository.group.TuitionGroup;

import java.util.List;

public interface TuitionRepositoryCustom {

    List<Tuition> findAllByOrderSeqDesc();

    List<TuitionGroup> findAllByStudentGroup();
}
