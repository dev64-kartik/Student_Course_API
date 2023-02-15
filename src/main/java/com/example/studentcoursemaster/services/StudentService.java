package com.example.studentcoursemaster.services;

import com.example.studentcoursemaster.dao.studentdao.StudentDAO;
import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.entities.Course;
import com.example.studentcoursemaster.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    public StudentDTO convertToDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO(
                student.getStudentId(),student.getName(),student.getCollege(),new HashSet<>());
        return studentDTO;
    }

    public Student convertToEntity(StudentDTO studentDTO) {
        Student student = new Student(
                studentDTO.getId(), studentDTO.getName(), studentDTO.getCollege());
        return student;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> students = studentDAO.getAllStudents();
        return students;
    }

    public StudentDTO getStudent(String id) {
        StudentDTO student = studentDAO.getStudent(id);
        return student;
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        return convertToDTO(studentDAO.addStudent(convertToEntity(studentDTO)));

    }
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        StudentDTO newStudentDTO = convertToDTO(studentDAO.updateStudent(student));
        newStudentDTO.setEnrolledCourses(studentDTO.getEnrolledCourses());
        return newStudentDTO;

    }

    public void deleteStudent(String id) {
        studentDAO.deleteStudent(id);
    }

    public boolean EnrollStudentIntoCourse(String studentId, String courseId) {
        return studentDAO.EnrollIntoCourse(studentId, courseId);
    }

    public void UnenrolLStudentFromCourse(String studentId, String courseId) {
        studentDAO.UnenrollFromCourse(studentId,courseId);
    }

}
