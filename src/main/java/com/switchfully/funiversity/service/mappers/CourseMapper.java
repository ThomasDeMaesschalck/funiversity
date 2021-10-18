package com.switchfully.funiversity.service.mappers;

import com.switchfully.funiversity.domain.Course;
import com.switchfully.funiversity.webapi.dtos.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private final ProfessorMapper professorMapper;

    @Autowired
    public CourseMapper(ProfessorMapper professorMapper) {
        this.professorMapper = professorMapper;
    }

    public List<CourseDTO> toDto(List<Course> courseList) {
        return courseList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public CourseDTO toDto(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getStudyPoints(), professorMapper.toDto(course.getProfessor()));
    }

    public Course toCourse(CourseDTO courseDTO) {
        return new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getStudyPoints(), professorMapper.toProfessor(courseDTO.getProfessor()));
    }
}
