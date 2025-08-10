package com.gosi.disclosure.web.rest;

import com.gosi.disclosure.domain.Gift;
import com.gosi.disclosure.repository.GiftRepository;
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
 * REST controller for managing {@link com.gosi.disclosure.domain.Gift}.
 */
@RestController
@RequestMapping("/api/gifts")
@Transactional
public class GiftResource {

    private static final Logger LOG = LoggerFactory.getLogger(GiftResource.class);

    private static final String ENTITY_NAME = "gift";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GiftRepository giftRepository;

    public GiftResource(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    /**
     * {@code POST  /gifts} : Create a new gift.
     *
     * @param gift the gift to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gift, or with status {@code 400 (Bad Request)} if the gift has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Gift> createGift(@Valid @RequestBody Gift gift) throws URISyntaxException {
        LOG.debug("REST request to save Gift : {}", gift);
        if (gift.getId() != null) {
            throw new BadRequestAlertException("A new gift cannot already have an ID", ENTITY_NAME, "idexists");
        }
        gift = giftRepository.save(gift);
        return ResponseEntity.created(new URI("/api/gifts/" + gift.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, gift.getId().toString()))
            .body(gift);
    }

    /**
     * {@code PUT  /gifts/:id} : Updates an existing gift.
     *
     * @param id the id of the gift to save.
     * @param gift the gift to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gift,
     * or with status {@code 400 (Bad Request)} if the gift is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gift couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Gift> updateGift(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody Gift gift)
        throws URISyntaxException {
        LOG.debug("REST request to update Gift : {}, {}", id, gift);
        if (gift.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gift.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        gift = giftRepository.save(gift);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gift.getId().toString()))
            .body(gift);
    }

    /**
     * {@code PATCH  /gifts/:id} : Partial updates given fields of an existing gift, field will ignore if it is null
     *
     * @param id the id of the gift to save.
     * @param gift the gift to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gift,
     * or with status {@code 400 (Bad Request)} if the gift is not valid,
     * or with status {@code 404 (Not Found)} if the gift is not found,
     * or with status {@code 500 (Internal Server Error)} if the gift couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Gift> partialUpdateGift(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Gift gift
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Gift partially : {}, {}", id, gift);
        if (gift.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gift.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!giftRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Gift> result = giftRepository
            .findById(gift.getId())
            .map(existingGift -> {
                if (gift.getGiftNameOrganizationGiven() != null) {
                    existingGift.setGiftNameOrganizationGiven(gift.getGiftNameOrganizationGiven());
                }
                if (gift.getGiftReason() != null) {
                    existingGift.setGiftReason(gift.getGiftReason());
                }
                if (gift.getGiftOfficialOccasion() != null) {
                    existingGift.setGiftOfficialOccasion(gift.getGiftOfficialOccasion());
                }
                if (gift.getGiftSpoilsQuickly() != null) {
                    existingGift.setGiftSpoilsQuickly(gift.getGiftSpoilsQuickly());
                }
                if (gift.getGiftForPersonalUse() != null) {
                    existingGift.setGiftForPersonalUse(gift.getGiftForPersonalUse());
                }
                if (gift.getGiftType() != null) {
                    existingGift.setGiftType(gift.getGiftType());
                }
                if (gift.getGiftEstimatedValue() != null) {
                    existingGift.setGiftEstimatedValue(gift.getGiftEstimatedValue());
                }
                if (gift.getGiftDateReceiving() != null) {
                    existingGift.setGiftDateReceiving(gift.getGiftDateReceiving());
                }
                if (gift.getGiftOwnDesire() != null) {
                    existingGift.setGiftOwnDesire(gift.getGiftOwnDesire());
                }
                if (gift.getGiftImpact() != null) {
                    existingGift.setGiftImpact(gift.getGiftImpact());
                }
                if (gift.getGiftReasonAcceptanceRejection() != null) {
                    existingGift.setGiftReasonAcceptanceRejection(gift.getGiftReasonAcceptanceRejection());
                }
                if (gift.getAmountCashOffered() != null) {
                    existingGift.setAmountCashOffered(gift.getAmountCashOffered());
                }
                if (gift.getOtherNotes() != null) {
                    existingGift.setOtherNotes(gift.getOtherNotes());
                }
                if (gift.getFormalOccasionVisit() != null) {
                    existingGift.setFormalOccasionVisit(gift.getFormalOccasionVisit());
                }

                return existingGift;
            })
            .map(giftRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gift.getId().toString())
        );
    }

    /**
     * {@code GET  /gifts} : get all the gifts.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("")
    public List<Gift> getAllGifts(@RequestParam(name = "filter", required = false) String filter) {
        if ("disclosure-is-null".equals(filter)) {
            LOG.debug("REST request to get all Gifts where disclosure is null");
            return StreamSupport.stream(giftRepository.findAll().spliterator(), false)
                .filter(gift -> gift.getDisclosure() == null)
                .toList();
        }
        LOG.debug("REST request to get all Gifts");
        return giftRepository.findAll();
    }

    /**
     * {@code GET  /gifts/:id} : get the "id" gift.
     *
     * @param id the id of the gift to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gift, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Gift> getGift(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Gift : {}", id);
        Optional<Gift> gift = giftRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gift);
    }

    /**
     * {@code DELETE  /gifts/:id} : delete the "id" gift.
     *
     * @param id the id of the gift to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Gift : {}", id);
        giftRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
