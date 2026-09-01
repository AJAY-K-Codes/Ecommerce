package com.project.Ecommerce.Exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    String resource;
    String field;
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(String resource, String field, String fieldName) {
        super(String.format("%s not found with %s : %s",resource,field,fieldName));
        this.resource = resource;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException( String fieldName, String resource,Long fieldId) {
        super(String.format("%s not found with %s : %d",fieldName,resource,fieldId));
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.resource = resource;
    }
}
