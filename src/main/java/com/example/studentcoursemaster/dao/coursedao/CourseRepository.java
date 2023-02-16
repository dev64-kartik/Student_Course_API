package com.example.studentcoursemaster.dao.coursedao;

import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.entities.Course;

import java.util.List;

public interface CourseRepository {

    List<CourseDTO> fetchAllCourses();

    CourseDTO fetchCourse(String id);

    Course storeCourse(Course course);

    Course updateCourse(Course newCourse);

    void deleteCourse(String id);

}
