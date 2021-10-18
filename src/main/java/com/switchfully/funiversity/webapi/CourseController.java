package com.switchfully.funiversity.webapi;

import com.switchfully.funiversity.service.CourseService;
import com.switchfully.funiversity.service.mappers.CourseMapper;
import com.switchfully.funiversity.webapi.dtos.CourseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<CourseDTO> getAllCourses(@RequestParam(required = false) String studyPoints) {
        if (studyPoints == null) {
            logger.info("Retrieved all courses");
            return courseService.getAll();
        } else {
            logger.info("Retrieved all courses with study points " + studyPoints);
            return courseService.getAllWithStudyPointsEqualTo(Integer.parseInt(studyPoints));
        }
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO save(@RequestBody CourseDTO courseDTO) {
        logger.info("Saved course " + courseDTO.toString());
        return courseMapper.toDto(courseService.save(courseDTO));
    }
}
