package com.motorbikes_hiring.service.motorbikesService;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeCreationRequest;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import com.motorbikes_hiring.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikesService {
  @Autowired
  private MotorbikesRepository motorbikesRepository;

  @Autowired
  private UserRepository userRepository;

  public List<Motorbikes> getMotorbikes () {
    return motorbikesRepository.findAll();
  }


  public void createMotorBike (MotorbikeCreationRequest request) throws IllegalAccessException {
    User user = userRepository.getById(request.getUserId());

    Motorbikes motorbikes = new Motorbikes(
        request.getTitle(), request.getDescription(), request.getContactInfo(), request.getCost(), request.getDurationDay(), user
    );
    motorbikesRepository.save(motorbikes);
  }

  public Motorbikes getMotorbike(Long id) {
    return motorbikesRepository.findById(id).get();
  }

  public void deleteMotorbike (Long id) {
    Motorbikes motorbikes = motorbikesRepository.getById(id);
    motorbikesRepository.delete(motorbikes);
  }
}
