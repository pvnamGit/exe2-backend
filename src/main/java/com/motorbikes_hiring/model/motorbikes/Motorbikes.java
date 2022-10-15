package com.motorbikes_hiring.model.motorbikes;

import com.motorbikes_hiring.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorbikes {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;
  private double cost;
  private int durationDay;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private String filePath = null;
  private Boolean status = true;

  public Motorbikes(String title, String description, double cost, int durationDay) {
    this.title = title;
    this.description = description;
    this.cost = cost;
    this.durationDay = durationDay;
  }
}
