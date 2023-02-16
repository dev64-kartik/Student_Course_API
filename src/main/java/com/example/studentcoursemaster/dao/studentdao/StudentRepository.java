package com.example.studentcoursemaster.dao.studentdao;

import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.entities.Student;

import java.util.List;

public interface StudentRepository {

    List<StudentDTO> fetchAllStudents();

    StudentDTO fetchStudent(String id);

    Student storeStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudent(String id);

    boolean EnrollStudentInCourse(String studentId, String courseId);

    void UnenrollStudentFromCourse(String studentId, String courseId);

}
