package com.example.studentcoursemaster.dao.studentdao;

import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAO {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.fetchAllStudents();
    }

    public StudentDTO getStudent(String id) {
        return studentRepository.fetchStudent(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.storeStudent(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteStudent(id);
    }

    public boolean EnrollIntoCourse(String studentId, String courseId) {
        return studentRepository.EnrollStudentInCourse(studentId, courseId);
    }

    public void UnenrollFromCourse(String studentId, String courseId) {
        studentRepository.UnenrollStudentFromCourse(studentId, courseId);
    }

}
