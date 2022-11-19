package com.motorbikes_hiring.payload.request.motorbikes;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MotorbikeUpdateRequest  {
private String title;
  private String description;
  private String contactInfo;
  private Long userId;
  private Double cost ;
  private Integer durationDay;
  private String files ;
}
