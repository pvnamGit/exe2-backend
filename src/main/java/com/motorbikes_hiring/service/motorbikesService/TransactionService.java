package com.motorbikes_hiring.service.motorbikesService;

import com.motorbikes_hiring.model.transactions.Transactions;
import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.motorbikes.TransactionRequest;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import com.motorbikes_hiring.repository.motorbikes.TransactionRepository;
import com.motorbikes_hiring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service

public class TransactionService {
    @Autowired
    private MotorbikesRepository motorbikesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public  static  int generrateRandomInt(){
        return (int)(Math.random() * (999999 - 111111 + 1) + 111111);
    }

    public Transactions createTransaction (TransactionRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        Transactions transaction = new Transactions(request.getTransactionId(), user);
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transactions getTransactionNumber(Long id){
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("Not found transaction");
                });
        return transactions;

    }

}
