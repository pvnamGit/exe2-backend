package com.motorbikes_hiring.model.motorbikes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorbikes implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime createdAt = LocalDateTime.now();

  private LocalDateTime updatedAt = LocalDateTime.now();
  private String title = "";
  private String description = "";

  private String contactInfo = "";
  private double cost = 0;
  private int durationDay = 0;
  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;
  private String filePath = "";
  private Boolean status = true;

  public Motorbikes(String title, String description, String contactInfo, double cost, int durationDay, String filePath, User user) {
    this.title = title;
    this.description = description;
    this.contactInfo = contactInfo;
    this.cost = cost;
    this.durationDay = durationDay;
    this.filePath = filePath;
    this.user = user;
  }
}
