package com.example.api.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private String name;
	private String college;
	
	
	//@JsonIgnore
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable( 
				name = "student_courses",
				joinColumns = @JoinColumn( name = "student_id"),
				inverseJoinColumns = @JoinColumn( name = "course_id")
			)
	private Set<Course> enrolledCourses = new HashSet<>();
	
	public void setEnrolledCourses(Set<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}

	public Student()
	{
		
	}

	public Student(int id, String name, String college, Set<Course> enrolledCourses) {
		super();
		Id = id;
		this.name = name;
		this.college = college;
		this.enrolledCourses = enrolledCourses;
	}
	
	

	public Set<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
    
	public boolean enrollInCourse(Course course) {
		
		return enrolledCourses.add(course);
	}
	
	public boolean unenrollFromCourse(Course course)
	{
		return enrolledCourses.remove(course);
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	

}
