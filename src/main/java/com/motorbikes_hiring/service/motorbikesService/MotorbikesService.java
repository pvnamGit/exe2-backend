package com.motorbikes_hiring.service.motorbikesService;

import com.dropbox.core.DbxException;
import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.payload.request.motorbikes.MotorbikeCreationRequest;
import com.motorbikes_hiring.repository.motorbikes.MotorbikesRepository;
import com.motorbikes_hiring.repository.user.UserRepository;
import com.motorbikes_hiring.service.dropboxService.DropboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MotorbikesService {
  @Autowired
  private MotorbikesRepository motorbikesRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DropboxService dropboxService;

  public List<Motorbikes> getMotorbikes () {
    return motorbikesRepository.findAll();
  }


  public void createMotorBike (MotorbikeCreationRequest request) throws IllegalAccessException, DbxException, IOException {
    User user = userRepository.getById(request.getUserId());
    String filePath = "";
    Motorbikes motorbikes = new Motorbikes(
        request.getTitle(), request.getDescription(), request.getContactInfo(), request.getCost(), request.getDurationDay(), filePath, user
    );
    if (request.getFiles() != null) {
      Random rand = new Random();

// Obtain a number between [0 - 49].
      Integer randomFolder = rand.nextInt(50);

      dropboxService.createFolder(Long.toString(user.getId()), randomFolder.toString());
      filePath = (String) dropboxService.uploadFile(request.getFiles(), Long.toString(user.getId()), randomFolder.toString());
    }

    motorbikes.setFilePath(filePath);
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
