package com.motorbikes_hiring.payload.request.motorbikes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TransactionRequest {
  private Long userId;
  private Integer transactionId;
}
