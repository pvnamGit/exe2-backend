package com.motorbikes_hiring.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import com.motorbikes_hiring.model.role.Role;
import com.motorbikes_hiring.model.transactions.Transactions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_account",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String username;
  private String email;
  private String fullName;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private String phone;
  private String address;
  private String avatar;
  private String facebookUrl;
  private Boolean canCrud = false;

  private Boolean isRequestPayment = false;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String authorizationToken;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Instant expireAuthorization;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String activateToken;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Boolean activeStatus = false;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Boolean status = true;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long resetPasswordCode;
  @ManyToMany(fetch = EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  Set<Role> roles = new HashSet<>();
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "user")
  private List<Motorbikes> motorbikes;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "user")
  private List<Transactions> transactions;


  public User(String username, String email, String phone, String fullName, String password, String activateToken) {
    this.username = username;
    this.email = email;
    this.phone = phone;
    this.fullName = fullName;
    this.password = password;
    this.activateToken = activateToken;
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
