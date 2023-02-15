package com.example.studentcoursemaster.services;

import com.example.studentcoursemaster.dao.coursedao.CourseDAO;
import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.entities.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    public CourseDTO convertToDTO(Course course) {

        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO(
                course.getCourseId(),course.getName(),course.getFees(),course.getEducator(),new ArrayList<>());
        return courseDTO;
    }

    public Course convertToEntity(CourseDTO courseDTO) {
        Course course = new Course(
                courseDTO.getId(), courseDTO.getName(), courseDTO.getFees(), courseDTO.getEducator());
        return course;
    }

    public List<CourseDTO> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    public CourseDTO getCourse(String id) {
            return courseDAO.getCourse(id);
    }

    public CourseDTO addCourse(CourseDTO courseDTO) {

            return convertToDTO(courseDAO.addCourse(convertToEntity(courseDTO)));
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        CourseDTO newCourseDTO = convertToDTO(courseDAO.updateCourse(course));
        newCourseDTO.setEnrolledStudents(courseDTO.getEnrolledStudents());
        return newCourseDTO;
    }

    public void deleteCourse(String id) {
        courseDAO.deleteCourse(id);
    }

}
