package com.switchfully.funiversity.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class CourseRepository {

    private final ConcurrentHashMap<Integer, Course> courses;

    public CourseRepository(){
        this.courses = new ConcurrentHashMap<>();
    }

    public List<Course> getAll() {
        return courses.values().stream().collect(Collectors.toList());
    }

    public List<Course> getAllWithStudyPointsEqualTo(int studyPoints){
        return courses.values().stream().filter(c -> c.getStudyPoints() == studyPoints).collect(Collectors.toList());
    }

    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }


}
