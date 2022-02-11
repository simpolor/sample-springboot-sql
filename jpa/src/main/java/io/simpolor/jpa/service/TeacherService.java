package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.TeacherRepository;
import io.simpolor.jpa.repository.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAll() {

        return teacherRepository.findAll();
    }

    public Teacher get(Long teacherId) {

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if(!optionalTeacher.isPresent()){
            throw new IllegalArgumentException("teacherId : "+teacherId);
        }

        return optionalTeacher.get();
    }

    public void create(Teacher teacher) {

        teacherRepository.save(teacher);
    }

    public void update(Teacher teacher) {

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getTeacherId());
        if(!optionalTeacher.isPresent()){
            throw new IllegalArgumentException("teacherId : "+teacher.getTeacherId());
        }

        teacherRepository.save(teacher);
    }

    public void delete(Long teacherId) {

        teacherRepository.deleteById(teacherId);
    }
}
