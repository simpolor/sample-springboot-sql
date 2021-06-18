package io.simpolor.jpa.service;

import io.simpolor.jpa.repository.ParentRepository;
import io.simpolor.jpa.repository.entity.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public long getTotalCount() {
        return parentRepository.count();
    }

    public List<Parent> getAll() {

        return parentRepository.findAll();
    }

    public Parent get(long seq) {

        return parentRepository.findById(seq).orElseThrow(() -> new IllegalArgumentException("seq : "+seq));
    }

    public void create(Parent parent) {

        parentRepository.save(parent);
    }

    public void update(Parent parent) {

        parentRepository.findById(parent.getSeq())
                .orElseThrow(() -> new IllegalArgumentException("seq : "+parent.getSeq()));

        parentRepository.save(parent);
    }

    public void delete(long seq) {

        parentRepository.deleteById(seq);
    }

}
