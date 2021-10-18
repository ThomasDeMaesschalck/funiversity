package com.switchfully.funiversity.webapi;

import com.switchfully.funiversity.service.ProfessorService;
import com.switchfully.funiversity.service.mappers.ProfessorMapper;
import com.switchfully.funiversity.webapi.dtos.ProfessorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorMapper professorMapper;

    private final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    @Autowired
    public ProfessorController(ProfessorService professorService, ProfessorMapper professorMapper) {
        this.professorService = professorService;
        this.professorMapper = professorMapper;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessorDTO> getAllProfessors() {
        logger.info("Retrieved all professors");
        return professorService.getAll();
    }

    @GetMapping(produces = "application/json", path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDTO findById(@PathVariable("id") int id) {
        try {
            logger.info("Retrieving professor with id " + id);
            return professorService.findById(id);
        } catch (IllegalArgumentException exception) {
            logger.error("Could not find professor with id " + id + " " + exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "The id " + id + " does not exist", exception);
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorDTO save(@RequestBody ProfessorDTO professorDTO) {
        logger.info("Saved processor " + professorDTO.getFirstName() + " " + professorDTO.getLastName());
        return professorMapper.toDto(professorService.save(professorDTO));
    }

    @PutMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorDTO update(@RequestBody ProfessorDTO professorDto, @PathVariable int id) {
        try {
            logger.info("Updating professor with id " + id);
            return professorMapper.toDto(professorService.update(professorDto));
        } catch (IllegalArgumentException exception) {
            logger.error("Could not find professor with id " + id + " " + exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "The id " + id + " does not exist", exception);
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        try {
            logger.info("Deleting professor with id " + id);
            professorService.delete(id);
        } catch (IllegalArgumentException exception) {
            logger.error("Could not delete professor with id " + id + " " + exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "The id " + id + " does not exist", exception);
        }
    }
}
