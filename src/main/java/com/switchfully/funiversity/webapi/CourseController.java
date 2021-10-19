package com.switchfully.funiversity.webapi;

import com.switchfully.funiversity.service.CourseService;
import com.switchfully.funiversity.service.mappers.CourseMapper;
import com.switchfully.funiversity.webapi.dtos.CourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> getAllCourses(@RequestParam Optional<Integer> studyPoints) {

        if (studyPoints.isPresent()) {
            logger.info("Retrieving all courses with study points " + studyPoints.get());
            return courseService.getAllWithStudyPointsEqualTo(studyPoints.get());
        }

        logger.info("Retrieving all courses");
        return courseService.getAll();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO save(@RequestBody CourseDTO courseDTO) {
        try {
            logger.info("Saving course " + courseDTO.toString());
            return courseMapper.toDto(courseService.save(courseDTO));
        } catch (IllegalArgumentException exception) {
            logger.error("Could not find professor with id " + courseDTO.getProfessor().getId() + " " + exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "The professor id " + courseDTO.getProfessor().getId() + " does not exist", exception);
        }
    }
}
