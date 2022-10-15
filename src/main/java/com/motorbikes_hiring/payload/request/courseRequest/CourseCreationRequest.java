package com.motorbikes_hiring.payload.request.courseRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseCreationRequest {
    private String courseName;
    private String courseDescription;
    private int grade;
    private double cost;
    private int length;
    private Long subjectId;
}
