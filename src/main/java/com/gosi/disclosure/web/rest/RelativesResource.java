package com.gosi.disclosure.web.rest;

import com.gosi.disclosure.domain.Relatives;
import com.gosi.disclosure.repository.RelativesRepository;
import com.gosi.disclosure.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.gosi.disclosure.domain.Relatives}.
 */
@RestController
@RequestMapping("/api/relatives")
@Transactional
public class RelativesResource {

    private static final Logger LOG = LoggerFactory.getLogger(RelativesResource.class);

    private static final String ENTITY_NAME = "relatives";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelativesRepository relativesRepository;

    public RelativesResource(RelativesRepository relativesRepository) {
        this.relativesRepository = relativesRepository;
    }

    /**
     * {@code POST  /relatives} : Create a new relatives.
     *
     * @param relatives the relatives to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relatives, or with status {@code 400 (Bad Request)} if the relatives has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Relatives> createRelatives(@Valid @RequestBody Relatives relatives) throws URISyntaxException {
        LOG.debug("REST request to save Relatives : {}", relatives);
        if (relatives.getId() != null) {
            throw new BadRequestAlertException("A new relatives cannot already have an ID", ENTITY_NAME, "idexists");
        }
        relatives = relativesRepository.save(relatives);
        return ResponseEntity.created(new URI("/api/relatives/" + relatives.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, relatives.getId().toString()))
            .body(relatives);
    }

    /**
     * {@code PUT  /relatives/:id} : Updates an existing relatives.
     *
     * @param id the id of the relatives to save.
     * @param relatives the relatives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatives,
     * or with status {@code 400 (Bad Request)} if the relatives is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relatives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Relatives> updateRelatives(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Relatives relatives
    ) throws URISyntaxException {
        LOG.debug("REST request to update Relatives : {}, {}", id, relatives);
        if (relatives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relativesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        relatives = relativesRepository.save(relatives);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatives.getId().toString()))
            .body(relatives);
    }

    /**
     * {@code PATCH  /relatives/:id} : Partial updates given fields of an existing relatives, field will ignore if it is null
     *
     * @param id the id of the relatives to save.
     * @param relatives the relatives to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relatives,
     * or with status {@code 400 (Bad Request)} if the relatives is not valid,
     * or with status {@code 404 (Not Found)} if the relatives is not found,
     * or with status {@code 500 (Internal Server Error)} if the relatives couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Relatives> partialUpdateRelatives(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Relatives relatives
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Relatives partially : {}, {}", id, relatives);
        if (relatives.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relatives.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relativesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Relatives> result = relativesRepository
            .findById(relatives.getId())
            .map(existingRelatives -> {
                if (relatives.getNameDiscloser() != null) {
                    existingRelatives.setNameDiscloser(relatives.getNameDiscloser());
                }
                if (relatives.getNameRelative() != null) {
                    existingRelatives.setNameRelative(relatives.getNameRelative());
                }
                if (relatives.getJobTitleRelative() != null) {
                    existingRelatives.setJobTitleRelative(relatives.getJobTitleRelative());
                }
                if (relatives.getRelativeJobNumber() != null) {
                    existingRelatives.setRelativeJobNumber(relatives.getRelativeJobNumber());
                }
                if (relatives.getEmailRelative() != null) {
                    existingRelatives.setEmailRelative(relatives.getEmailRelative());
                }
                if (relatives.getRelativeExtensionNumber() != null) {
                    existingRelatives.setRelativeExtensionNumber(relatives.getRelativeExtensionNumber());
                }
                if (relatives.getRelativeRelationship() != null) {
                    existingRelatives.setRelativeRelationship(relatives.getRelativeRelationship());
                }
                if (relatives.getGeneralAdministrationRelative() != null) {
                    existingRelatives.setGeneralAdministrationRelative(relatives.getGeneralAdministrationRelative());
                }
                if (relatives.getAdministrationRelative() != null) {
                    existingRelatives.setAdministrationRelative(relatives.getAdministrationRelative());
                }

                return existingRelatives;
            })
            .map(relativesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, relatives.getId().toString())
        );
    }

    /**
     * {@code GET  /relatives} : get all the relatives.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relatives in body.
     */
    @GetMapping("")
    public List<Relatives> getAllRelatives() {
        LOG.debug("REST request to get all Relatives");
        return relativesRepository.findAll();
    }

    /**
     * {@code GET  /relatives/:id} : get the "id" relatives.
     *
     * @param id the id of the relatives to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relatives, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Relatives> getRelatives(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Relatives : {}", id);
        Optional<Relatives> relatives = relativesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relatives);
    }

    /**
     * {@code DELETE  /relatives/:id} : delete the "id" relatives.
     *
     * @param id the id of the relatives to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelatives(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Relatives : {}", id);
        relativesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
