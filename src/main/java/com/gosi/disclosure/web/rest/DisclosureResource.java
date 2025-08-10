package com.gosi.disclosure.web.rest;

import com.gosi.disclosure.domain.Disclosure;
import com.gosi.disclosure.repository.DisclosureRepository;
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
 * REST controller for managing {@link com.gosi.disclosure.domain.Disclosure}.
 */
@RestController
@RequestMapping("/api/disclosures")
@Transactional
public class DisclosureResource {

    private static final Logger LOG = LoggerFactory.getLogger(DisclosureResource.class);

    private static final String ENTITY_NAME = "disclosure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisclosureRepository disclosureRepository;

    public DisclosureResource(DisclosureRepository disclosureRepository) {
        this.disclosureRepository = disclosureRepository;
    }

    /**
     * {@code POST  /disclosures} : Create a new disclosure.
     *
     * @param disclosure the disclosure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new disclosure, or with status {@code 400 (Bad Request)} if the disclosure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Disclosure> createDisclosure(@Valid @RequestBody Disclosure disclosure) throws URISyntaxException {
        LOG.debug("REST request to save Disclosure : {}", disclosure);
        if (disclosure.getId() != null) {
            throw new BadRequestAlertException("A new disclosure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        disclosure = disclosureRepository.save(disclosure);
        return ResponseEntity.created(new URI("/api/disclosures/" + disclosure.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, disclosure.getId().toString()))
            .body(disclosure);
    }

    /**
     * {@code PUT  /disclosures/:id} : Updates an existing disclosure.
     *
     * @param id the id of the disclosure to save.
     * @param disclosure the disclosure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated disclosure,
     * or with status {@code 400 (Bad Request)} if the disclosure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the disclosure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Disclosure> updateDisclosure(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Disclosure disclosure
    ) throws URISyntaxException {
        LOG.debug("REST request to update Disclosure : {}, {}", id, disclosure);
        if (disclosure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, disclosure.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!disclosureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        disclosure = disclosureRepository.save(disclosure);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, disclosure.getId().toString()))
            .body(disclosure);
    }

    /**
     * {@code PATCH  /disclosures/:id} : Partial updates given fields of an existing disclosure, field will ignore if it is null
     *
     * @param id the id of the disclosure to save.
     * @param disclosure the disclosure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated disclosure,
     * or with status {@code 400 (Bad Request)} if the disclosure is not valid,
     * or with status {@code 404 (Not Found)} if the disclosure is not found,
     * or with status {@code 500 (Internal Server Error)} if the disclosure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Disclosure> partialUpdateDisclosure(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Disclosure disclosure
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Disclosure partially : {}, {}", id, disclosure);
        if (disclosure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, disclosure.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!disclosureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Disclosure> result = disclosureRepository
            .findById(disclosure.getId())
            .map(existingDisclosure -> {
                if (disclosure.getPurposeOfDisclosure() != null) {
                    existingDisclosure.setPurposeOfDisclosure(disclosure.getPurposeOfDisclosure());
                }
                if (disclosure.getCreationDate() != null) {
                    existingDisclosure.setCreationDate(disclosure.getCreationDate());
                }
                if (disclosure.getName() != null) {
                    existingDisclosure.setName(disclosure.getName());
                }
                if (disclosure.getJobTitle() != null) {
                    existingDisclosure.setJobTitle(disclosure.getJobTitle());
                }
                if (disclosure.getJobNumber() != null) {
                    existingDisclosure.setJobNumber(disclosure.getJobNumber());
                }
                if (disclosure.getExt() != null) {
                    existingDisclosure.setExt(disclosure.getExt());
                }
                if (disclosure.getPublicAdministration() != null) {
                    existingDisclosure.setPublicAdministration(disclosure.getPublicAdministration());
                }
                if (disclosure.getAdministration() != null) {
                    existingDisclosure.setAdministration(disclosure.getAdministration());
                }
                if (disclosure.getConfirm() != null) {
                    existingDisclosure.setConfirm(disclosure.getConfirm());
                }
                if (disclosure.getEmail() != null) {
                    existingDisclosure.setEmail(disclosure.getEmail());
                }
                if (disclosure.getEmailDirectManager() != null) {
                    existingDisclosure.setEmailDirectManager(disclosure.getEmailDirectManager());
                }
                if (disclosure.getNameDirectManager() != null) {
                    existingDisclosure.setNameDirectManager(disclosure.getNameDirectManager());
                }
                if (disclosure.getJobNumberDirectManager() != null) {
                    existingDisclosure.setJobNumberDirectManager(disclosure.getJobNumberDirectManager());
                }
                if (disclosure.getJobTitleDirectManager() != null) {
                    existingDisclosure.setJobTitleDirectManager(disclosure.getJobTitleDirectManager());
                }
                if (disclosure.getExtDirectManager() != null) {
                    existingDisclosure.setExtDirectManager(disclosure.getExtDirectManager());
                }
                if (disclosure.getAreThereRelatives() != null) {
                    existingDisclosure.setAreThereRelatives(disclosure.getAreThereRelatives());
                }
                if (disclosure.getFile() != null) {
                    existingDisclosure.setFile(disclosure.getFile());
                }
                if (disclosure.getFilename() != null) {
                    existingDisclosure.setFilename(disclosure.getFilename());
                }

                return existingDisclosure;
            })
            .map(disclosureRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, disclosure.getId().toString())
        );
    }

    /**
     * {@code GET  /disclosures} : get all the disclosures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of disclosures in body.
     */
    @GetMapping("")
    public List<Disclosure> getAllDisclosures() {
        LOG.debug("REST request to get all Disclosures");
        return disclosureRepository.findAll();
    }

    /**
     * {@code GET  /disclosures/:id} : get the "id" disclosure.
     *
     * @param id the id of the disclosure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the disclosure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Disclosure> getDisclosure(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Disclosure : {}", id);
        Optional<Disclosure> disclosure = disclosureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(disclosure);
    }

    /**
     * {@code DELETE  /disclosures/:id} : delete the "id" disclosure.
     *
     * @param id the id of the disclosure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisclosure(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Disclosure : {}", id);
        disclosureRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
