package com.example.api.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dao.CourseDatabaseService;
import com.example.api.entities.Course;


@CrossOrigin
@RestController
public class CourseController {
	
	@Autowired
	private CourseDatabaseService db;
	
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses()
	{
		List<Course> courses = db.getAllCourses();
		if(courses.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(courses));
		}
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable int id)
	{   
		Course course = db.getCourseById(id);
		if(course==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{
			return ResponseEntity.of(Optional.of(course));
		}
				
	}
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody Course course)
	{
		if(db.addCourse(course))
			return ResponseEntity.of(Optional.of(course));
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course,@PathVariable int id)
	{
		Course crs = db.getCourseById(id);
		if(crs==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{   
			course.setCourseId(id);
			if(db.addCourse(course))
			{   
				return ResponseEntity.of(Optional.of(course));
			}
			else
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	//Delete specific course
	
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable int id)
	{
		Course course = db.getCourseById(id);
		if(course==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		else
		{   
			db.deleteCourse(id);
			return ResponseEntity.of(Optional.of("Course Deleted Successfully !"));
		}
	}
	
	
	// Delete all courses
	@DeleteMapping("/courses")
	public ResponseEntity<String> deleteAllCourses()
	{   
		db.deleteAllCourses();
		return ResponseEntity.of(Optional.of("All Courses Deleted !"));
	}

}
