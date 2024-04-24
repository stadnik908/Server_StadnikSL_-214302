package com.certification.repo;

import com.certification.model.Passing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassingRepo extends JpaRepository<Passing, Long> {
    Passing findByOwner_IdAndTest_Id(Long ownerId, Long testId);

    Passing findByOwner_IdAndTest_IdAndStatus(Long ownerId, Long testId, boolean status);
}
