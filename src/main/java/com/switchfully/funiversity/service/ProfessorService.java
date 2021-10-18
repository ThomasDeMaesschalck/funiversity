package com.switchfully.funiversity.service;

import com.switchfully.funiversity.domain.Professor;
import com.switchfully.funiversity.domain.ProfessorRepository;
import com.switchfully.funiversity.service.mappers.ProfessorMapper;
import com.switchfully.funiversity.webapi.dtos.ProfessorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorMapper professorMapper;
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorMapper professorMapper, ProfessorRepository professorRepository) {
        this.professorMapper = professorMapper;
        this.professorRepository = professorRepository;
    }

    public List<ProfessorDTO> getAll(){
        return professorMapper.toDto(professorRepository.getAll());
    }

    public ProfessorDTO findById(int id){
        return professorMapper.toDto(professorRepository.getById(id));
    }

    public Professor save(ProfessorDTO professorDTO){
        return professorRepository.save(professorMapper.toProfessor(professorDTO));
    }

    public Professor update(ProfessorDTO professorDTO){
        return professorRepository.update(professorMapper.toProfessor(professorDTO));
    }

    public void delete(int id){
        professorRepository.delete(id);
    }
}
