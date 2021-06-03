package io.simpolor.jpa.model.pet;

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
public class PetResponse {

    private long seq;
    private String kind;
    private String name;
    private int age;

    public static PetResponse of(Pet pet){

        PetResponse response = new PetResponse();
        response.setSeq(pet.getSeq());
        response.setKind(pet.getKind());
        response.setName(pet.getName());
        response.setAge(pet.getAge());

        return response;
    }

    public static List<PetResponse> of(List<Pet> pets){

        return pets.stream().map(PetResponse::of).collect(Collectors.toList());
    }
}
