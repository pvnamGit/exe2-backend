package com.motorbikes_hiring.repository.refreshToken;

import com.motorbikes_hiring.model.user.User;
import com.motorbikes_hiring.model.refreshToken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);
}
