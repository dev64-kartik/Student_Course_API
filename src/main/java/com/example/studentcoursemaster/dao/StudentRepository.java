package com.example.studentcoursemaster.dao;

import com.example.studentcoursemaster.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	
	public boolean existsByNameAndCollege(String name,String college);

}
