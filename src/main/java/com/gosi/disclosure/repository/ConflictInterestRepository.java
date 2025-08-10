package com.gosi.disclosure.repository;

import com.gosi.disclosure.domain.ConflictInterest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ConflictInterest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConflictInterestRepository extends JpaRepository<ConflictInterest, Long> {}
