package io.simpolor.jpa.repository;

import io.simpolor.jpa.repository.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByNameIn(List<String> names);

}
