package com.switchfully.funiversity.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ProfessorRepository {

    private final ConcurrentHashMap<Integer, Professor> professors;

    public ProfessorRepository() {
        this.professors = new ConcurrentHashMap<>();
    }

    public List<Professor> getAll() {
        return professors.values().stream().collect(Collectors.toList());
    }

    public Professor getById(Integer id) throws IllegalArgumentException {
        var foundByProfessor = professors.get(id);
        if (foundByProfessor == null) {
            throw new IllegalArgumentException("Professor with id " + id + " not found");
        }
        return foundByProfessor;
    }

    public Professor save(Professor professor) {
        professors.put(professor.getId(), professor);
        return professor;
    }

    public Professor update(Professor professor){
        if(!professors.containsKey(professor.getId())){
            throw new IllegalArgumentException("Professor with id " + professor.getId() + " not found");
        }
        professors.put(professor.getId(), professor);
        return professor;
    }

    public void delete(Integer id){
        if(!professors.containsKey(id)){
            throw new IllegalArgumentException("No professor with id " + id);
        }
        professors.remove(id);
    }


}
