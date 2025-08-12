package com.gosi.disclosure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gosi.disclosure.domain.UserInfo;

/**
 * Spring Data JPA repository for the UserInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	List<UserInfo> findAllByEmail(String email);
}
