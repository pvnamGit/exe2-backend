package com.motorbikes_hiring.payload.response.motorbikeResponse;

import com.motorbikes_hiring.model.transactions.Transactions;
import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
   private Long id;
   private int randomNumber;
   public TransactionResponse(Transactions transactions){
      this.id = transactions.getId();
      this.randomNumber = transactions.getTransactionNumber();

   }

}
