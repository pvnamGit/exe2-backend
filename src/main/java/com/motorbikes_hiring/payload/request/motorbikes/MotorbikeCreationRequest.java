package com.motorbikes_hiring.payload.request.motorbikes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MotorbikeCreationRequest implements Serializable {
  private String title = "";
  private String description = "";
  private String contactInfo = "";
  private Long userId;
  private double cost = 0;
  private int durationDay = 0;
  private MultipartFile files = null;
}
