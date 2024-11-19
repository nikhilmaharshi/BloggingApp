package com.nikhil.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    Integer fieldValue;
    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue) {
        super("No " + resourceName + " found with " + fieldName + " = " + fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
