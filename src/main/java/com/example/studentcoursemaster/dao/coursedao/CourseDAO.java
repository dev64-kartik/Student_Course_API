package com.example.studentcoursemaster.dao.coursedao;


import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.entities.Course;
import com.example.studentcoursemaster.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDAO {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.fetchAllCourses();
    }

    public CourseDTO getCourse(String id) {
        return courseRepository.fetchCourse(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.storeCourse(course);
    }

    public Course updateCourse(Course newCourse) {
        return courseRepository.updateCourse(newCourse);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteCourse(id);
    }

}
