package com.motorbikes_hiring.controller.motorbikesController;

import com.motorbikes_hiring.model.transactions.Transactions;
import com.motorbikes_hiring.payload.response.motorbikeResponse.TransactionResponse;
import com.motorbikes_hiring.payload.response.responseMessage.ErrorMessageResponse;
import com.motorbikes_hiring.service.motorbikesService.MotorbikesService;
import com.motorbikes_hiring.service.motorbikesService.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/motorbikes")
public class MotorbikesController {

  @Autowired
  private MotorbikesService motorbikesService;
  @Autowired
  private TransactionService transactionService;

  @GetMapping("")
  public ResponseEntity<?> getMotorbikes () {
    try {
      return ResponseEntity.ok(motorbikesService.getMotorbikes());
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getTransactions (@PathVariable("id") Long id ) {
    try {
      return ResponseEntity.ok(transactionService.getTransactionNumber(id));
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/2/{id}")
  public ResponseEntity<?> createTransactionNumber (@PathVariable("id") Long id ) {
    try {
      Transactions transactions = transactionService.getTransactionNumber(id);
      return ResponseEntity.ok().body(new TransactionResponse(transactions));
    }catch (NoSuchElementException ex) {
      return ResponseEntity.badRequest().body(new ErrorMessageResponse(ex.getMessage()));
    }
  }

}
