package com.example.api.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RequestResponse {
	
	public static <T> ResponseEntity<T> generateResponse(T resource)
	{
		if(resource==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(resource));
	}
	
	public static ResponseEntity<?> generateResponseForGet(List<?> resources){
		
		if(resources.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else
			return ResponseEntity.of(Optional.of(resources));

	}
	

}
