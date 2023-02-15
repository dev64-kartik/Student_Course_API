package com.example.studentcoursemaster.dao;

import com.example.studentcoursemaster.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
*  TODO
*   - Integrate MongoDB
*  */


@Service
public class CourseDatabaseService {

    @Autowired
    private CourseRepository courserepo;

    public List<Course> getAllCourses() {

        List<Course> res = new ArrayList<Course>();
        courserepo.findAll().forEach(res::add);
        return res;

    }

    public boolean IsCourseExists(Course course) {
        if (courserepo.existsByNameAndFeesAndEducator(course.getName(), course.getFees(), course.getEducator()))
            return true;
        else
            return false;

    }

    public Course getCourseById(int id) {
        return courserepo.findById(id).orElse(null);

    }

    public boolean addCourse(Course course) {
        if (IsCourseExists(course))
            return false;
        else {
            courserepo.save(course);
            return true;
        }
    }

    public void deleteCourse(int id) {
        courserepo.deleteById(id);
    }

    public void deleteAllCourses() {
        courserepo.deleteAll();
    }

    public void saveCourse(Course course) {
        courserepo.save(course);
    }
}
