package io.simpolor.jpa.domain.pet;

import io.simpolor.jpa.repository.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {

    private long seq;
    private String kind;
    private String name;
    private int age;

    public Pet toPet(){

        Pet pet = new Pet();
        pet.setKind(this.kind);
        pet.setName(this.name);
        pet.setAge(this.age);

        return pet;
    }
}
