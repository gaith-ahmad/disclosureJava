package com.gosi.disclosure.web.rest;

import com.gosi.disclosure.domain.ManagerInfo;
import com.gosi.disclosure.repository.ManagerInfoRepository;
import com.gosi.disclosure.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.gosi.disclosure.domain.ManagerInfo}.
 */
@RestController
@RequestMapping("/api/manager-infos")
@Transactional
public class ManagerInfoResource {

    private static final Logger LOG = LoggerFactory.getLogger(ManagerInfoResource.class);

    private static final String ENTITY_NAME = "managerInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManagerInfoRepository managerInfoRepository;

    public ManagerInfoResource(ManagerInfoRepository managerInfoRepository) {
        this.managerInfoRepository = managerInfoRepository;
    }

    /**
     * {@code POST  /manager-infos} : Create a new managerInfo.
     *
     * @param managerInfo the managerInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new managerInfo, or with status {@code 400 (Bad Request)} if the managerInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ManagerInfo> createManagerInfo(@Valid @RequestBody ManagerInfo managerInfo) throws URISyntaxException {
        LOG.debug("REST request to save ManagerInfo : {}", managerInfo);
        if (managerInfo.getId() != null) {
            throw new BadRequestAlertException("A new managerInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        managerInfo = managerInfoRepository.save(managerInfo);
        return ResponseEntity.created(new URI("/api/manager-infos/" + managerInfo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, managerInfo.getId().toString()))
            .body(managerInfo);
    }

    /**
     * {@code PUT  /manager-infos/:id} : Updates an existing managerInfo.
     *
     * @param id the id of the managerInfo to save.
     * @param managerInfo the managerInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated managerInfo,
     * or with status {@code 400 (Bad Request)} if the managerInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the managerInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ManagerInfo> updateManagerInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ManagerInfo managerInfo
    ) throws URISyntaxException {
        LOG.debug("REST request to update ManagerInfo : {}, {}", id, managerInfo);
        if (managerInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, managerInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!managerInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        managerInfo = managerInfoRepository.save(managerInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, managerInfo.getId().toString()))
            .body(managerInfo);
    }

    /**
     * {@code PATCH  /manager-infos/:id} : Partial updates given fields of an existing managerInfo, field will ignore if it is null
     *
     * @param id the id of the managerInfo to save.
     * @param managerInfo the managerInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated managerInfo,
     * or with status {@code 400 (Bad Request)} if the managerInfo is not valid,
     * or with status {@code 404 (Not Found)} if the managerInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the managerInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ManagerInfo> partialUpdateManagerInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ManagerInfo managerInfo
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ManagerInfo partially : {}, {}", id, managerInfo);
        if (managerInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, managerInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!managerInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ManagerInfo> result = managerInfoRepository
            .findById(managerInfo.getId())
            .map(existingManagerInfo -> {
                if (managerInfo.getEmailDirectManager() != null) {
                    existingManagerInfo.setEmailDirectManager(managerInfo.getEmailDirectManager());
                }
                if (managerInfo.getNameDirectManager() != null) {
                    existingManagerInfo.setNameDirectManager(managerInfo.getNameDirectManager());
                }
                if (managerInfo.getJobNumberDirectManager() != null) {
                    existingManagerInfo.setJobNumberDirectManager(managerInfo.getJobNumberDirectManager());
                }
                if (managerInfo.getJobTitleDirectManager() != null) {
                    existingManagerInfo.setJobTitleDirectManager(managerInfo.getJobTitleDirectManager());
                }
                if (managerInfo.getExtDirectManager() != null) {
                    existingManagerInfo.setExtDirectManager(managerInfo.getExtDirectManager());
                }

                return existingManagerInfo;
            })
            .map(managerInfoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, managerInfo.getId().toString())
        );
    }

    /**
     * {@code GET  /manager-infos} : get all the managerInfos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of managerInfos in body.
     */
    @GetMapping("")
    public List<ManagerInfo> getAllManagerInfos(@RequestParam(name = "filter", required = false) String filter) {
        if ("userinfo-is-null".equals(filter)) {
            LOG.debug("REST request to get all ManagerInfos where userInfo is null");
            return StreamSupport.stream(managerInfoRepository.findAll().spliterator(), false)
                .filter(managerInfo -> managerInfo.getUserInfo() == null)
                .toList();
        }
        LOG.debug("REST request to get all ManagerInfos");
        return managerInfoRepository.findAll();
    }

    /**
     * {@code GET  /manager-infos/:id} : get the "id" managerInfo.
     *
     * @param id the id of the managerInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the managerInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ManagerInfo> getManagerInfo(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ManagerInfo : {}", id);
        Optional<ManagerInfo> managerInfo = managerInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(managerInfo);
    }

    /**
     * {@code DELETE  /manager-infos/:id} : delete the "id" managerInfo.
     *
     * @param id the id of the managerInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagerInfo(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ManagerInfo : {}", id);
        managerInfoRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
