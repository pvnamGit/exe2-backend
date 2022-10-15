package com.motorbikes_hiring.payload.response.motorbikeResponse;

import com.motorbikes_hiring.model.transactions.Transactions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
   Transactions transactions;

}
