package com.example.studentcoursemaster.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Document(collection = "Students")
@Data
@AllArgsConstructor
public class Student {
	
	@Id
	private String studentId;
	private String name;
	private String college;


}
