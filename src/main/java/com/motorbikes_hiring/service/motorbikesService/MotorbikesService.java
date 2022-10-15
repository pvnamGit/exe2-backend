package com.motorbikes_hiring.service.motorbikesService;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeCreationRequest;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikesService {
  @Autowired
  private MotorbikesRepository motorbikesRepository;

  public List<Motorbikes> getMotorbikes () {
    return motorbikesRepository.findAll();
  }

  public void createMotorBike (MotorbikeCreationRequest request) {
    Motorbikes motorbikes = new Motorbikes(
        request.getTitle(), request.getDescription(), request.getCost(), request.getDurationDay()
    );
    motorbikesRepository.save(motorbikes);
  }

  public void deleteMotorbike (Long id) {
    Motorbikes motorbikes = motorbikesRepository.getById(id);
    motorbikesRepository.delete(motorbikes);
  }
}
