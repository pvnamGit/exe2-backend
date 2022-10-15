package com.motorbikes_hiring.payload.request.userRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {

    @JsonProperty(required = false)
    private String fullName;

    @JsonProperty(required = false)
    @Size(max = 15)
    private String phone;

    @JsonProperty(required = false)
    private String grade;

    @JsonProperty(required = false)
    private String address;

    @JsonProperty(required = false)
    private String avatar;

    @JsonProperty(required = false)
    private String facebookUrl;

    @JsonProperty(required = false)
    private String affiliate;

    @JsonProperty(required = false)
    private String gpa;

    @JsonProperty(required = false)
    private String gender;

    @JsonProperty(required = false)
    private String birthday;

}
