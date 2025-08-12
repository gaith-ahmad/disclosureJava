package com.gosi.disclosure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gosi.disclosure.domain.Disclosure;
import java.util.List;


/**
 * Spring Data JPA repository for the Disclosure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisclosureRepository extends JpaRepository<Disclosure, Long> {
	List<Disclosure> findAllByEmail(String email);
}
