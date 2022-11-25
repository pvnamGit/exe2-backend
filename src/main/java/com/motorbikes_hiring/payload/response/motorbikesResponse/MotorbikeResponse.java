package com.motorbikes_hiring.payload.response.motorbikesResponse;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorbikeResponse {
  private Boolean status = true;
  private Motorbikes data;
  private Long userId;
  
}
