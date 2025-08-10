package com.gosi.disclosure.repository;

import com.gosi.disclosure.domain.Relatives;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Relatives entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Long> {}
