package com.example.api.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.api.entities.Course;

public interface CourseRepository  extends CrudRepository<Course,Integer>{
	
	public boolean existsByNameAndFeesAndEducator(String name,int fees, String educator);
	

}

