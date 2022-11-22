package com.motorbikes_hiring.controller.motorbikesController;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeCreationRequest;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeUpdateRequest;
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
  public ResponseEntity<?> getMotorbikes(
      @RequestParam(name = "title", required = false, defaultValue = "") String title,
      @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
    try {
      MotorbikesListResponse response = motorbikesService.getMotorbikes(title, page);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  @GetMapping("/your_motorbikes")
  public ResponseEntity<?> getMotorbikesbyId(
      @RequestParam(name = "title", required = false, defaultValue = "") String title,
      @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,@RequestParam(name = "userId") Long id ) {
    try {
      MotorbikesListResponse response = motorbikesService.getMotorbikesByUserId(title, page,id);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


  @GetMapping("/motorbike/{id}")
  public ResponseEntity<?> getMotorbike(@PathVariable(name = "id") Long id) {
    try {
      Motorbikes motorbike = motorbikesService.getMotorbike(id);
      return ResponseEntity.ok(new MotorbikeResponse(true, motorbike));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping(value = "/motorbikes")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createMotorbike(MotorbikeCreationRequest request) throws IOException {
    try {
      motorbikesService.createMotorBike(request);
      return ResponseEntity.ok().body(new SuccessfulMessageResponse("Create motorbike success"));
    } catch (Exception exception) {
      return ResponseEntity.badRequest().body(exception.getMessage());
    }
  }

  @PutMapping("/motorbike/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> updateMotorbike(@RequestBody MotorbikeUpdateRequest request,
  @PathVariable(name = "id") Long id) {
        try{
           motorbikesService.updateMotorbike(request, id );
                return ResponseEntity.ok(new  SuccessfulMessageResponse("update motorbike success"));

        }catch(Exception e){
          return ResponseEntity.badRequest().body(e.getMessage());
        }
    
  }

}
