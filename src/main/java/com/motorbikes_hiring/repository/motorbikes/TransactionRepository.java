package com.motorbikes_hiring.repository.motorbikes;

import com.motorbikes_hiring.model.transactions.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
