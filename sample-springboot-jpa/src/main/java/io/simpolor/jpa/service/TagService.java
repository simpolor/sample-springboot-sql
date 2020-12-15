package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.TagRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAll() {

        return tagRepository.findAll();
    }

    public Tag get(long seq) {

        return tagRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("seq : "+seq));
    }

    public Tag getStudent(long studentSeq) {

        return tagRepository.findByStudentSeq(studentSeq).orElse(null);
    }

    public void register(String title, Student student) {

        tagRepository.save(new Tag(title, student));
    }



}
