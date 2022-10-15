package com.motorbikes_hiring.payload.request.motorbikes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class MotorbikeCreationRequest implements Serializable {
  private String title;
  private String description;
  private double cost;
  private int durationDay;
  private MultipartFile files;
}
