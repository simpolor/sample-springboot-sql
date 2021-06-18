package io.simpolor.jpa.model;

import io.simpolor.jpa.repository.entity.Pet;
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
public class PetDto {

    private long seq;
    private String kind;
    private String name;
    private int age;

    public Pet toEntity(){

        return Pet.builder()
                .kind(this.kind)
                .name(this.name)
                .age(this.age)
                .build();
    }

    public static PetDto of(Pet pet){

        PetDto response = new PetDto();
        response.setSeq(pet.getSeq());
        response.setKind(pet.getKind());
        response.setName(pet.getName());
        response.setAge(pet.getAge());

        return response;
    }

    public static List<PetDto> of(List<Pet> pets){

        return pets.stream().map(PetDto::of).collect(Collectors.toList());
    }
}
