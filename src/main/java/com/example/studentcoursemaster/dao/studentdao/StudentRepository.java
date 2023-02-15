package com.example.studentcoursemaster.dao.studentdao;

import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.entities.Student;

import java.util.List;

public interface StudentRepository {

    public List<StudentDTO> fetchAllStudents();

    public StudentDTO fetchStudent(String id);

    public Student storeStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudent(String id);

    public boolean EnrollStudentInCourse(String studentId , String courseId);

    public void UnenrollStudentFromCourse(String studentId,String courseId);

}
