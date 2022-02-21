package io.simpolor.querydsl.repository;

import io.simpolor.querydsl.repository.entity.Tuition;
import io.simpolor.querydsl.repository.querydsl.TuitionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TuitionRepository extends JpaRepository<Tuition, Long>, TuitionRepositoryCustom {

}
