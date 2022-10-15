package com.motorbikes_hiring.service.motorbikesService;

import com.motorbikes_hiring.model.transactions.Transactions;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import com.motorbikes_hiring.repository.motorbikes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service

public class TransactionService {
    @Autowired
    private MotorbikesRepository motorbikesRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public  static  int generrateRandomInt(){
        return (int)(Math.random() * (999999 - 111111 + 1) + 111111);
    }
    public Transactions createRandomNumber(Long userId){
        Transactions transactions = new Transactions(generrateRandomInt());
        transactionRepository.save(transactions);
        return transactions;
    }

    public Transactions getTransactionNumber(Long id){
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("Not found transaction");
                });
        return transactions;

    }

}
