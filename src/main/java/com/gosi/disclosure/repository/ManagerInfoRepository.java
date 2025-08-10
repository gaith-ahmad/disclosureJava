package com.gosi.disclosure.repository;

import com.gosi.disclosure.domain.ManagerInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ManagerInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManagerInfoRepository extends JpaRepository<ManagerInfo, Long> {}
