package com.motorbikes_hiring.controller.transactions;

import com.motorbikes_hiring.payload.response.responseMessage.SuccessfulMessageResponse;
import com.motorbikes_hiring.service.motorbikesService.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<?> createTransactions(@RequestHeader(name = "Authorization") String accessToken, @RequestBody Integer transactionId) {
    try {
      transactionService.createTransaction(accessToken, transactionId);
      return ResponseEntity.ok().body(new SuccessfulMessageResponse("Confirm payment successful, your subscription will be checked and activated within 1 hour"));
    } catch (Exception exception) {
      return ResponseEntity.badRequest().body(exception.getMessage());
    }
  }
}
