package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTitle(String title);

    Optional<Tag> findByStudentSeq(Long studentSeq);
}
