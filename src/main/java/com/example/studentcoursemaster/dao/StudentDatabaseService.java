package com.example.studentcoursemaster.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentcoursemaster.entities.Student;

@Service
public class StudentDatabaseService {
	
	@Autowired
	private StudentRepository studentrepo;
	
public List<Student> getAllStudents(){
		
		List<Student> res = new ArrayList<Student>();
		studentrepo.findAll().forEach(res :: add);
		return res;
		
	}
	
	public boolean IsStudentExists(Student student)
	{
		if(studentrepo.existsByNameAndCollege(student.getName(),student.getCollege()))
			return true;
		else
			return false;

	}
	public Student getStudentById(int id)
	{
		return studentrepo.findById(id).orElse(null);
		
	}
	
	public boolean addStudent(Student student)
	{   
		if(IsStudentExists(student))
			return false;
		else
		{   
			studentrepo.save(student);
			return true;
		}
	}
	
	public void saveStudent(Student student)
	{
		studentrepo.save(student);
	}
	
	public void deleteStudent(int id) {
		studentrepo.deleteById(id);
	}
	
	public void deleteAllStudents() {
		studentrepo.deleteAll();
	}
	

}
