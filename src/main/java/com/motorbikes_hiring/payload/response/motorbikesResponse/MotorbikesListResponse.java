package com.motorbikes_hiring.payload.response.motorbikesResponse;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorbikesListResponse {
  private Boolean status = true;
  private List<Motorbikes> data;
}
