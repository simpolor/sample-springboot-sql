package io.simpolor.querydsl.service;

import io.simpolor.querydsl.repository.TuitionRepository;
import io.simpolor.querydsl.repository.entity.Tuition;
import io.simpolor.querydsl.repository.group.TuitionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TuitionService {

    private final TuitionRepository expenseRepository;

    public List<Tuition> getAll() {

        return expenseRepository.findAll();
    }

    public List<Tuition> getAllByOrderSeqDesc() {

        return expenseRepository.findAllByOrderSeqDesc();
    }

    public List<TuitionGroup> getAllByStudentGroup() {

        return expenseRepository.findAllByStudentGroup();
    }

}
