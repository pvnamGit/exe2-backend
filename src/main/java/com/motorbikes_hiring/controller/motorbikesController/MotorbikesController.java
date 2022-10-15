package com.motorbikes_hiring.controller.motorbikesController;

import com.motorbikes_hiring.service.motorbikesService.MotorbikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/motorbikes")
public class MotorbikesController {

  @Autowired
  private MotorbikesService motorbikesService;

  @GetMapping("")
  public ResponseEntity<?> getMotorbikes () {
    try {
      return ResponseEntity.ok(motorbikesService.getMotorbikes());
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
