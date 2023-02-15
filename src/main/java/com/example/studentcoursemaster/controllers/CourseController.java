package com.example.studentcoursemaster.controllers;

import java.util.List;
import java.util.Optional;

import com.example.studentcoursemaster.dao.CourseDatabaseService;
import com.example.studentcoursemaster.dto.CourseDTO;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.studentcoursemaster.entities.Course;


@CrossOrigin
@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getCourses()
	{
		List<CourseDTO> courses = courseService.getAllCourses();
		if(courses.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(courses));
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable String id)
	{   
		CourseDTO course = courseService.getCourse(id);
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
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course)
	{
		CourseDTO courseDTO = courseService.addCourse(course);
		if(courseDTO != null)
			return ResponseEntity.of(Optional.of(course));
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO course,@PathVariable String id)
	{
		course.setId(id);
		CourseDTO crs = courseService.updateCourse(course);
		if(crs==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(crs));
	}
	
	//Delete specific course
	
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable String id)
	{
		courseService.deleteCourse(id);
		return ResponseEntity.of(Optional.of("Course Deleted Successfully !"));
	}
	
	
	/*// Delete all courses
	@DeleteMapping("/courses")
	public ResponseEntity<String> deleteAllCourses()
	{   
		db.deleteAllCourses();
		return ResponseEntity.of(Optional.of("All Courses Deleted !"));
	}*/

}
