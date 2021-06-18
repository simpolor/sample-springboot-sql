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

    public long getTotalCount() {
        return teacherRepository.count();
    }

    public List<Teacher> getAll() {

        return teacherRepository.findAll();
    }

    public List<Teacher> getAll(List<Long> sequences) {

        return teacherRepository.findAllBySeqIn(sequences);
    }

    public Teacher get(long seq) {

        Optional<Teacher> teacherOptional = teacherRepository.findById(seq);
        if(!teacherOptional.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return teacherOptional.get();
    }

    public void create(Teacher teacher) {

        teacherRepository.save(teacher);
    }

    public void update(Teacher teacher) {

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getSeq());
        if(!optionalTeacher.isPresent()){
            throw new IllegalArgumentException("seq : "+teacher.getSeq());
        }

        teacherRepository.save(teacher);
    }

    public void delete(long seq) {

        teacherRepository.deleteById(seq);
    }
}
