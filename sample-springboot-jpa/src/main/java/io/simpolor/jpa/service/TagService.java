package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.TagRepository;
import io.simpolor.jpa.repository.entity.Student;
import io.simpolor.jpa.repository.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {

    private  final TagRepository tagRepository;

    public List<Tag> getAll() {

        return tagRepository.findAll();
    }

    public Tag get(long seq) {

        Optional<Tag> optionalTag = tagRepository.findById(seq);
        if(!optionalTag.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalTag.get();
    }

    public void create(Tag tag, Student student) {

        tag.setStudent(student);

        tagRepository.save(tag);
    }

    public void update(Tag tag, Student student) {

        Optional<Tag> optionalTag = tagRepository.findById(tag.getSeq());
        if(!optionalTag.isPresent()){
            throw new IllegalArgumentException("seq : "+tag.getSeq());
        }

        tag.setStudent(student);

        tagRepository.save(tag);
    }



}
