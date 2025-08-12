package com.gosi.disclosure.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gosi.disclosure.domain.Disclosure;
import com.gosi.disclosure.domain.Employee;
import com.gosi.disclosure.domain.UserInfo;
import com.gosi.disclosure.service.OldSharPintService;
import com.gosi.disclosure.service.dto.DisclosureDTO;
import com.gosi.disclosure.service.dto.EmployeeDTO;
import com.gosi.disclosure.service.dto.UserInfoDTO;
import com.gosi.disclosure.web.rest.errors.BadRequestAlertException;

import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.gosi.disclosure.domain.Relatives}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OldSharPintResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private static final Logger LOG = LoggerFactory.getLogger(OldSharPintResource.class);

	private final OldSharPintService oldSharPintService;

	public OldSharPintResource(OldSharPintService oldSharPintService) {
		this.oldSharPintService = oldSharPintService;
	}

	@GetMapping("/Disclosure/GetAllitem")
	public List<DisclosureDTO> getAllDisclosure(@RequestParam(name = "Email", required = false) String email) {
		LOG.debug("REST request to get all disclosure");
		if (email != null) {
			return oldSharPintService.disclosureFindAllByEmail(email);
		}
		return null;
	}

	@PostMapping("/Disclosure/CreateNewitem")
	public ResponseEntity<DisclosureDTO> createDisclosureNewItem(@RequestBody DisclosureDTO disclosure)
			throws URISyntaxException {
		LOG.debug("REST request to save Disclosure : {}", disclosure);
		if (disclosure.getId() != null) {
			throw new BadRequestAlertException("A new disclosure cannot already have an ID", "disclosure", "idexists");
		}
		disclosure = oldSharPintService.save(disclosure);
		return ResponseEntity
				.created(new URI("/Disclosure/CreateNewitem" + disclosure.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, "disclosure", disclosure.getId().toString()))
				.body(disclosure);
	}

	@GetMapping("/PDAP/GetEmpInfoByEmail")
	public UserInfoDTO getLDAPUserInfoByEmail(@RequestParam(name = "email", required = false) String email) {
		if (email != null) {
			return oldSharPintService.userInfoFindAllByEmail(email);
		}
		return null;
	}

	@GetMapping("/PDAP/GetEmpInfoByUserId")
	public ResponseEntity<UserInfoDTO> getLDAPUserInfoByEmail(@RequestParam(name = "userId", required = false) Long id) {
		if (id != null) {
			Optional<UserInfoDTO> userInfo = oldSharPintService.userInfoFindById(id);
			return ResponseUtil.wrapOrNotFound(userInfo);
		}
		return null;
	}

	@GetMapping("/PDAP/GetUsersByName")
	public List<EmployeeDTO> getLDAPUserByName(@RequestParam(name = "name", required = false) String name) {
		if (name != null) {
			return oldSharPintService.employeeFindAllByUsername(name);
		}
		return null;
	}

	@GetMapping("/GetUserInfo")
	public ResponseEntity<UserInfoDTO> getUserInfo() {
		UserInfoDTO user = new UserInfoDTO();
		user.setEmail("test@test.com");
		user.setFullName("test");
		user.setLoginName("test");
		return ResponseEntity.ok(user);
	}

	@GetMapping("/Disclosure/GetDisclosureUserHistory")
	public ResponseEntity<List<Disclosure>> getHistory() {

		return ResponseEntity.ok(new ArrayList());
	}

}
