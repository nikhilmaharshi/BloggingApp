package com.nikhil.blog.exceptions;

public class ResourceNotFoundExceptionForUsername extends RuntimeException {
    String resourceName;
    String fieldName;
    String fieldValue;
    public ResourceNotFoundExceptionForUsername(String resourceName, String fieldName, String fieldValue) {
        super("No " + resourceName + " found with " + fieldName + " = " + fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
