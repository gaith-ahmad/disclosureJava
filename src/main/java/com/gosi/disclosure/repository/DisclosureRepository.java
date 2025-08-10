package com.gosi.disclosure.repository;

import com.gosi.disclosure.domain.Disclosure;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Disclosure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisclosureRepository extends JpaRepository<Disclosure, Long> {}
