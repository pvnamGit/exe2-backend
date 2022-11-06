package com.motorbikes_hiring.repository.motorbikes;

import com.motorbikes_hiring.model.motorbikes.Motorbikes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikesRepository extends JpaRepository<Motorbikes, Long> {
  Page<Motorbikes> findAll(Pageable pageable);

  Page<Motorbikes> findAllByTitleContains(String title, Pageable pageable);
}
