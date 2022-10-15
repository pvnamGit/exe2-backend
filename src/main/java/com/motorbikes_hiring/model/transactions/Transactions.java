package com.motorbikes_hiring.model.transactions;

import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int transactionNumber;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

    public Transactions( int transactionNumber, User user) {
      this.transactionNumber= transactionNumber;
      this.user = user;
    }
}
