package com.example.studentcoursemaster.dao.coursedao;

import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.entities.Course;
import com.example.studentcoursemaster.entities.Student;

import java.util.List;

public interface CourseRepository {

    public List<CourseDTO> fetchAllCourses();

    public CourseDTO fetchCourse(String id);

    public Course storeCourse(Course course);

    public Course updateCourse(Course newCourse);

    public void deleteCourse(String id);

}
