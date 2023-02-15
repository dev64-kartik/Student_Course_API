package com.example.studentcoursemaster.controllers;

import java.util.List;
import java.util.Optional;

import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.services.StudentService;
import com.example.studentcoursemaster.entities.Course;
import com.example.studentcoursemaster.entities.Student;
import com.example.studentcoursemaster.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	/*@Autowired
	private CourseService courseService;*/
	
	@GetMapping("")
	public ResponseEntity<List<StudentDTO>> getStudents()
	{
		List<StudentDTO> students = studentService.getAllStudents();
		if(students.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(students));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable String id)
	{   
		StudentDTO student = studentService.getStudent(id);
		if(student==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(student));
		}
				
	}
	
	@PostMapping("")
	public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO student)
	{
		StudentDTO studentDTO = studentService.addStudent(student);
		if(studentDTO != null)
			return ResponseEntity.of(Optional.of(student));
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO student,@PathVariable String id)
	{
		student.setId(id);
		StudentDTO crs = studentService.updateStudent(student);
		if(crs==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(crs));
	}
	
	//Delete specific student
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable String id)
	{
		studentService.deleteStudent(id);
		return ResponseEntity.of(Optional.of("Course Deleted Successfully !"));
	}

	//Enroll student in course
	
	@PostMapping("/{id}/courses/{courseId}")
	public ResponseEntity<String> enrollInCourse(@PathVariable String id, @PathVariable String courseId)
	{
		if (studentService.EnrollStudentIntoCourse(id,courseId)) {
			return ResponseEntity.of(Optional.of("Successfully Enrolled !"));
		} else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@DeleteMapping("/{id}/courses/{courseId}")
	public ResponseEntity<String> unenrollFromCourse(@PathVariable String id,@PathVariable String courseId)
	{
		studentService.UnenrolLStudentFromCourse(id,courseId);
		return ResponseEntity.of(Optional.of("Successfully Unenrolled from Course !"));
	}
    
	
	
}
