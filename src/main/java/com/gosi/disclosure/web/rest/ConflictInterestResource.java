package com.gosi.disclosure.web.rest;

import com.gosi.disclosure.domain.ConflictInterest;
import com.gosi.disclosure.repository.ConflictInterestRepository;
import com.gosi.disclosure.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link com.gosi.disclosure.domain.ConflictInterest}.
 */
@RestController
@RequestMapping("/api/conflict-interests")
@Transactional
public class ConflictInterestResource {

    private static final Logger LOG = LoggerFactory.getLogger(ConflictInterestResource.class);

    private static final String ENTITY_NAME = "conflictInterest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConflictInterestRepository conflictInterestRepository;

    public ConflictInterestResource(ConflictInterestRepository conflictInterestRepository) {
        this.conflictInterestRepository = conflictInterestRepository;
    }

    /**
     * {@code POST  /conflict-interests} : Create a new conflictInterest.
     *
     * @param conflictInterest the conflictInterest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conflictInterest, or with status {@code 400 (Bad Request)} if the conflictInterest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ConflictInterest> createConflictInterest(@RequestBody ConflictInterest conflictInterest)
        throws URISyntaxException {
        LOG.debug("REST request to save ConflictInterest : {}", conflictInterest);
        if (conflictInterest.getId() != null) {
            throw new BadRequestAlertException("A new conflictInterest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        conflictInterest = conflictInterestRepository.save(conflictInterest);
        return ResponseEntity.created(new URI("/api/conflict-interests/" + conflictInterest.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, conflictInterest.getId().toString()))
            .body(conflictInterest);
    }

    /**
     * {@code PUT  /conflict-interests/:id} : Updates an existing conflictInterest.
     *
     * @param id the id of the conflictInterest to save.
     * @param conflictInterest the conflictInterest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conflictInterest,
     * or with status {@code 400 (Bad Request)} if the conflictInterest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conflictInterest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ConflictInterest> updateConflictInterest(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ConflictInterest conflictInterest
    ) throws URISyntaxException {
        LOG.debug("REST request to update ConflictInterest : {}, {}", id, conflictInterest);
        if (conflictInterest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, conflictInterest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!conflictInterestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        conflictInterest = conflictInterestRepository.save(conflictInterest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conflictInterest.getId().toString()))
            .body(conflictInterest);
    }

    /**
     * {@code PATCH  /conflict-interests/:id} : Partial updates given fields of an existing conflictInterest, field will ignore if it is null
     *
     * @param id the id of the conflictInterest to save.
     * @param conflictInterest the conflictInterest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conflictInterest,
     * or with status {@code 400 (Bad Request)} if the conflictInterest is not valid,
     * or with status {@code 404 (Not Found)} if the conflictInterest is not found,
     * or with status {@code 500 (Internal Server Error)} if the conflictInterest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ConflictInterest> partialUpdateConflictInterest(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ConflictInterest conflictInterest
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ConflictInterest partially : {}, {}", id, conflictInterest);
        if (conflictInterest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, conflictInterest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!conflictInterestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ConflictInterest> result = conflictInterestRepository
            .findById(conflictInterest.getId())
            .map(existingConflictInterest -> {
                if (conflictInterest.getConflictInterestClassification() != null) {
                    existingConflictInterest.setConflictInterestClassification(conflictInterest.getConflictInterestClassification());
                }
                if (conflictInterest.getConflictInterestEntityIndividual() != null) {
                    existingConflictInterest.setConflictInterestEntityIndividual(conflictInterest.getConflictInterestEntityIndividual());
                }
                if (conflictInterest.getConflictInterestDateArose() != null) {
                    existingConflictInterest.setConflictInterestDateArose(conflictInterest.getConflictInterestDateArose());
                }
                if (conflictInterest.getConflictInterestImpact() != null) {
                    existingConflictInterest.setConflictInterestImpact(conflictInterest.getConflictInterestImpact());
                }
                if (conflictInterest.getRelationshipEntityIndividual() != null) {
                    existingConflictInterest.setRelationshipEntityIndividual(conflictInterest.getRelationshipEntityIndividual());
                }
                if (conflictInterest.getAffectPermission() != null) {
                    existingConflictInterest.setAffectPermission(conflictInterest.getAffectPermission());
                }
                if (conflictInterest.getCaseDetails() != null) {
                    existingConflictInterest.setCaseDetails(conflictInterest.getCaseDetails());
                }
                if (conflictInterest.getDiscloseInspector() != null) {
                    existingConflictInterest.setDiscloseInspector(conflictInterest.getDiscloseInspector());
                }
                if (conflictInterest.getFacilityRegisteredInsuranceName() != null) {
                    existingConflictInterest.setFacilityRegisteredInsuranceName(conflictInterest.getFacilityRegisteredInsuranceName());
                }
                if (conflictInterest.getOffice() != null) {
                    existingConflictInterest.setOffice(conflictInterest.getOffice());
                }

                return existingConflictInterest;
            })
            .map(conflictInterestRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, conflictInterest.getId().toString())
        );
    }

    /**
     * {@code GET  /conflict-interests} : get all the conflictInterests.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conflictInterests in body.
     */
    @GetMapping("")
    public List<ConflictInterest> getAllConflictInterests(@RequestParam(name = "filter", required = false) String filter) {
        if ("disclosure-is-null".equals(filter)) {
            LOG.debug("REST request to get all ConflictInterests where disclosure is null");
            return StreamSupport.stream(conflictInterestRepository.findAll().spliterator(), false)
                .filter(conflictInterest -> conflictInterest.getDisclosure() == null)
                .toList();
        }
        LOG.debug("REST request to get all ConflictInterests");
        return conflictInterestRepository.findAll();
    }

    /**
     * {@code GET  /conflict-interests/:id} : get the "id" conflictInterest.
     *
     * @param id the id of the conflictInterest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conflictInterest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConflictInterest> getConflictInterest(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ConflictInterest : {}", id);
        Optional<ConflictInterest> conflictInterest = conflictInterestRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(conflictInterest);
    }

    /**
     * {@code DELETE  /conflict-interests/:id} : delete the "id" conflictInterest.
     *
     * @param id the id of the conflictInterest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConflictInterest(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ConflictInterest : {}", id);
        conflictInterestRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
