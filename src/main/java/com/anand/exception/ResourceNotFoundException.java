package com.anand.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	  private String resourceName;
	    private String fieldName;
	    private Object fieldValue;
	    private LocalDateTime timestamp;

	    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
	        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
	        this.resourceName = resourceName;
	        this.fieldName = fieldName;
	        this.fieldValue = fieldValue;
	        this.timestamp = LocalDateTime.now();
	    }

	    public String getResourceName() {
	        return resourceName;
	    }

	    public String getFieldName() {
	        return fieldName;
	    }

	    public Object getFieldValue() {
	        return fieldValue;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }

}
