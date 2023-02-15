package com.example.studentcoursemaster.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;




//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="courseId")
@Document(collection = "Courses")
@Data
@AllArgsConstructor
public class Course {
	
	@Id
	private String courseId;
	private String name;
	private int fees;
	private String educator;



}
