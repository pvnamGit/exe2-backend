package com.motorbikes_hiring.service.motorbikesService;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.model.transactions.Transactions;
import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeTransactionRequest;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import com.motorbikes_hiring.repository.motorbikes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Random;
@Service

public class TransactionService {
    @Autowired
    private MotorbikesRepository motorbikesRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public  static  int generrateRandomInt(){
        return (int)(Math.random() * (999999 - 111111 + 1) + 111111);
    }
    public void createRandomNumber(MotorbikeTransactionRequest request){
        Transactions transactions = new Transactions(request.getUser_id(), generrateRandomInt());
        transactionRepository.save(transactions);
    }

    public Transactions getTransactionNumber(Long id){
        Transactions transactions = transactionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("Not found transaction");
                });
        return transactions;

    }

}
