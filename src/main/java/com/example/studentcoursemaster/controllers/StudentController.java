package com.example.studentcoursemaster.controllers;

import java.util.List;
import java.util.Optional;

import com.example.studentcoursemaster.dao.CourseDatabaseService;
import com.example.studentcoursemaster.dao.StudentDatabaseService;
import com.example.studentcoursemaster.entities.Course;
import com.example.studentcoursemaster.entities.Student;
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
	private StudentDatabaseService db;
	
	@Autowired
	private CourseDatabaseService c_db;
	
	@GetMapping("")
	public ResponseEntity<List<Student>> getStudents()
	{
		List<Student> students = db.getAllStudents();
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
	public ResponseEntity<Student> getStudent(@PathVariable int id)
	{   
		Student student = db.getStudentById(id);
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
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		if(db.addStudent(student))
			return ResponseEntity.status(HttpStatus.CREATED).body(student);
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable int id)
	{
		Student crs = db.getStudentById(id);
		if(crs==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{   
			student.setId(id);
			student.setEnrolledCourses(student.getEnrolledCourses());
			if(db.addStudent(student))
			{   
				return ResponseEntity.of(Optional.of(student));
			}
			else
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	//Delete specific student
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id)
	{
		Student student = db.getStudentById(id);
		if(student==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{   
			db.deleteStudent(id);
			return ResponseEntity.of(Optional.of("Student Deleted Successfully !"));
		}
	}
	
	
	// Delete all students
	@DeleteMapping("")
	public ResponseEntity<String> deleteAllCourses()
	{   
		db.deleteAllStudents();
		return ResponseEntity.of(Optional.of("All Students Deleted !"));
	}
	
	//Enroll student in course
	
	@PostMapping("/{id}/courses/{courseId}")
	public ResponseEntity<Student> enrollInCourse(@PathVariable int id,@PathVariable int courseId)
	{
		Student student = db.getStudentById(id);
		if(student==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
		{   
			Course course = c_db.getCourseById(courseId);
			if(course==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			else
			{   
				if(student.enrollInCourse(course))
				{   
					
					course.enrollStudent(student);
					db.saveStudent(student);
					c_db.saveCourse(course);
					return ResponseEntity.of(Optional.of(student));
				
				}
				else
					return ResponseEntity.status(HttpStatus.CONFLICT).build();
				
			}
			
		}
		
	}
	
	@DeleteMapping("/{id}/courses/{courseId}")
	public ResponseEntity<Student> unenrollFromCourse(@PathVariable int id,@PathVariable int courseId)
	{
		Student student = db.getStudentById(id);
		if(student==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
		{   
			Course course = c_db.getCourseById(courseId);
			if(course==null)
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			else
			{   
				student.unenrollFromCourse(course);
				course.unenrollStudent(student);
				db.saveStudent(student);
				c_db.saveCourse(course);
				return ResponseEntity.of(Optional.of(student));
				
			}
			
		}
	}
    
	
	

}
