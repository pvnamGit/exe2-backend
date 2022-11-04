package com.motorbikes_hiring.controller.motorbikesController;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeCreationRequest;
import com.motorbikes_hiring.payload.response.motorbikesResponse.MotorbikeResponse;
import com.motorbikes_hiring.payload.response.motorbikesResponse.MotorbikesListResponse;
import com.motorbikes_hiring.payload.response.responseMessage.SuccessfulMessageResponse;
import com.motorbikes_hiring.service.motorbikesService.MotorbikesService;
import com.motorbikes_hiring.service.motorbikesService.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/public")
public class MotorbikesController {

  @Autowired
  private MotorbikesService motorbikesService;
  @Autowired
  private TransactionService transactionService;

  @GetMapping("/motorbikes")
  public ResponseEntity<?> getMotorbikes () {
    try {
      List<Motorbikes> motorbikesList = motorbikesService.getMotorbikes();
      return ResponseEntity.ok(new MotorbikesListResponse(true, motorbikesList));
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/motorbike/{id}")
  public ResponseEntity<?> getMotorbike (@PathVariable(name = "id") Long id) {
    try {
      Motorbikes motorbike = motorbikesService.getMotorbike(id);
      return ResponseEntity.ok(new MotorbikeResponse(true, motorbike));
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/motorbikes")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createMotorbike (MotorbikeCreationRequest request) throws IOException {
    try {
      motorbikesService.createMotorBike(request);
      return ResponseEntity.ok().body(new SuccessfulMessageResponse("Create motorbike success"));
    } catch (Exception exception) {
      return ResponseEntity.badRequest().body(exception.getMessage());
    }
  }


}
