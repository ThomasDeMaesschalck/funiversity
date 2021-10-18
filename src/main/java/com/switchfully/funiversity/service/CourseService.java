package com.switchfully.funiversity.service;

import com.switchfully.funiversity.domain.Course;
import com.switchfully.funiversity.domain.CourseRepository;
import com.switchfully.funiversity.service.mappers.CourseMapper;
import com.switchfully.funiversity.webapi.dtos.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final ProfessorService professorService;

    @Autowired
    public CourseService(CourseMapper courseMapper, CourseRepository courseRepository, ProfessorService professorService) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
        this.professorService = professorService;
    }

    public List<CourseDTO> getAll() {
        return courseMapper.toDto(courseRepository.getAll());
    }

    public List<CourseDTO> getAllWithStudyPointsEqualTo(int studyPoints) {
        return courseMapper.toDto(courseRepository.getAllWithStudyPointsEqualTo(studyPoints));
    }

    public Course save(CourseDTO courseDTO) {
        try {
            professorService.findById(courseDTO.getProfessor().getId());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Professor with id " + courseDTO.getProfessor().getId() + " does not exist.");
        }
        return courseRepository.save(courseMapper.toCourse(courseDTO));
    }
}
