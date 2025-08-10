package com.gosi.disclosure.repository;

import com.gosi.disclosure.domain.Gift;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Gift entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {}
