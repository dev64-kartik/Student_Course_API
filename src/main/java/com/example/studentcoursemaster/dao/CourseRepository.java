package com.example.studentcoursemaster.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.studentcoursemaster.entities.Course;

public interface CourseRepository  extends CrudRepository<Course,Integer>{
	
	public boolean existsByNameAndFeesAndEducator(String name,int fees, String educator);
	

}

