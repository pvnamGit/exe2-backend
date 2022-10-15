package com.motorbikes_hiring.payload.request.courseRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CourseUpdateRequest {
    private String courseName;
    private String courseDescription;
    private Double cost;
    private Integer grade;
    private Integer length;
    private Long subjectId;
}
