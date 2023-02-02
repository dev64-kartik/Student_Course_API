package com.example.api.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private String name;
	private int fees;
	private String educator;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "enrolledCourses")
	private List<Student> enrolledStudents = new ArrayList<>();
	
	
    public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
    
    public boolean enrollStudent(Student student) {
    	return enrolledStudents.add(student);
    }
    
    public boolean unenrollStudent(Student student)
    {
    	return enrolledStudents.remove(student);
    }
    
	public Course()
    {
    	
    }
	
	public Course(int courseId, String name, int fees, String educator) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.fees = fees;
		this.educator = educator;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getFees() {
		return fees;
	}


	public void setFees(int fees) {
		this.fees = fees;
	}


	public String getEducator() {
		return educator;
	}


	public void setEducator(String educator) {
		this.educator = educator;
	}
	
}
