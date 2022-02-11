package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.ClassroomRepository;
import io.simpolor.jpa.repository.entity.Classroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public List<Classroom> getAll() {

        return classroomRepository.findAll();
    }

    public Classroom get(long classroomId) {

        Optional<Classroom> optionalClassroom = classroomRepository.findById(classroomId);
        if(!optionalClassroom.isPresent()){
            throw new IllegalArgumentException("classroomId : "+classroomId);
        }

        return optionalClassroom.get();
    }

    public Classroom create(Classroom classroom) {

        return classroomRepository.saveAndFlush(classroom);
    }

    public void update(Classroom classroom) {

        classroomRepository.findById(classroom.getClassroomId())
                .orElseThrow(() -> new IllegalArgumentException("classroomId : "+classroom.getClassroomId()));

        classroomRepository.saveAndFlush(classroom);
    }

    public void delete(long classroomId) {

        classroomRepository.deleteById(classroomId);
    }

}
