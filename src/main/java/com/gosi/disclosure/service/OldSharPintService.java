package com.gosi.disclosure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gosi.disclosure.repository.DisclosureRepository;
import com.gosi.disclosure.repository.EmployeeRepository;
import com.gosi.disclosure.repository.UserInfoRepository;
import com.gosi.disclosure.service.dto.DisclosureDTO;
import com.gosi.disclosure.service.dto.EmployeeDTO;
import com.gosi.disclosure.service.dto.UserInfoDTO;
import com.gosi.disclosure.service.mapper.DisclosureMapper;
import com.gosi.disclosure.service.mapper.EmployeeMapper;
import com.gosi.disclosure.service.mapper.UserInfoMapper;

/**
 * Service for sending emails asynchronously.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class OldSharPintService {
	private final DisclosureRepository disclosureRepository;
	private final UserInfoRepository userInfoRepository;
	private final EmployeeRepository employeeRepository;

	public OldSharPintService(DisclosureRepository disclosureRepository, UserInfoRepository userInfoRepository,
			EmployeeRepository employeeRepository) {
		this.disclosureRepository = disclosureRepository;
		this.userInfoRepository = userInfoRepository;
		this.employeeRepository = employeeRepository;
	}

	public List<DisclosureDTO> disclosureFindAllByEmail(String email) {
		if (email != null) {
			return disclosureRepository.findAllByEmail(email).stream().map(e -> DisclosureMapper.toDto(e)).toList();
		}
		return disclosureRepository.findAll().stream().map(e -> DisclosureMapper.toDto(e)).toList();
	}

	public DisclosureDTO save(DisclosureDTO disclosure) {
		return DisclosureMapper.toDto(disclosureRepository.save(DisclosureMapper.toEntity(disclosure)));
	}

	public UserInfoDTO userInfoFindAllByEmail(String email) {
		if (email != null) {
			return userInfoRepository.findAllByEmail(email).stream().map(e -> UserInfoMapper.toDto(e)).toList().stream()
					.findFirst().orElse(null);
		}
		return null;
	}

	public Optional<UserInfoDTO> userInfoFindById(Long id) {
		return userInfoRepository.findById(id).map(UserInfoMapper::toDto);
	}

	public List<EmployeeDTO> employeeFindAllByUsername(String name) {
		if (name != null) {
			return employeeRepository.findAllByUsernameContaining(name).stream().map(e -> EmployeeMapper.toDto(e))
					.toList();
		}
		return null;
	}

}