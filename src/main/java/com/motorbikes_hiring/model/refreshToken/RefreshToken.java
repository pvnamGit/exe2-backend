package com.motorbikes_hiring.model.refreshToken;

import com.motorbikes_hiring.model.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

}
