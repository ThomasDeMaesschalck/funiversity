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

    @Autowired
    public CourseService(CourseMapper courseMapper, CourseRepository courseRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAll() {
        return courseMapper.toDto(courseRepository.getAll());
    }

    public List<CourseDTO> getAllWithStudyPointsEqualTo(int studyPoints) {
        return courseMapper.toDto(courseRepository.getAllWithStudyPointsEqualTo(studyPoints));
    }

    public Course save(CourseDTO courseDTO) {
        return courseRepository.save(courseMapper.toCourse(courseDTO));
    }
}
