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

    public void register(String title, Student student) {

        Tag tag = Tag.builder()
                .title(title)
                .student(student)
                .build();
        tagRepository.save(tag);
    }



}
