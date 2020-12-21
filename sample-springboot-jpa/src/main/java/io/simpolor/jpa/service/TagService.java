package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.TagRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void register(Tag tag, Student student) {

        tag.setStudent(student);

        tagRepository.save(tag);
    }

    public void modify(Tag tag, Student student) {

        Optional<Tag> optionalTag = tagRepository.findById(tag.getSeq());
        if(!optionalTag.isPresent()){
            throw new IllegalArgumentException("seq : "+tag.getSeq());
        }

        tag.setStudent(student);

        tagRepository.save(tag);
    }



}
