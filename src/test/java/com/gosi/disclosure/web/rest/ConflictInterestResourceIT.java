package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.ConflictInterestAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.ConflictInterest;
import com.gosi.disclosure.repository.ConflictInterestRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ConflictInterestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ConflictInterestResourceIT {

    private static final String DEFAULT_CONFLICT_INTEREST_CLASSIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CONFLICT_INTEREST_CLASSIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFLICT_INTEREST_ENTITY_INDIVIDUAL = "AAAAAAAAAA";
    private static final String UPDATED_CONFLICT_INTEREST_ENTITY_INDIVIDUAL = "BBBBBBBBBB";

    private static final String DEFAULT_CONFLICT_INTEREST_DATE_AROSE = "AAAAAAAAAA";
    private static final String UPDATED_CONFLICT_INTEREST_DATE_AROSE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFLICT_INTEREST_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_CONFLICT_INTEREST_IMPACT = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIONSHIP_ENTITY_INDIVIDUAL = "AAAAAAAAAA";
    private static final String UPDATED_RELATIONSHIP_ENTITY_INDIVIDUAL = "BBBBBBBBBB";

    private static final String DEFAULT_AFFECT_PERMISSION = "AAAAAAAAAA";
    private static final String UPDATED_AFFECT_PERMISSION = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_CASE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_DISCLOSE_INSPECTOR = "AAAAAAAAAA";
    private static final String UPDATED_DISCLOSE_INSPECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_FACILITY_REGISTERED_INSURANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FACILITY_REGISTERED_INSURANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/conflict-interests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ConflictInterestRepository conflictInterestRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConflictInterestMockMvc;

    private ConflictInterest conflictInterest;

    private ConflictInterest insertedConflictInterest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConflictInterest createEntity() {
        return new ConflictInterest()
            .conflictInterestClassification(DEFAULT_CONFLICT_INTEREST_CLASSIFICATION)
            .conflictInterestEntityIndividual(DEFAULT_CONFLICT_INTEREST_ENTITY_INDIVIDUAL)
            .conflictInterestDateArose(DEFAULT_CONFLICT_INTEREST_DATE_AROSE)
            .conflictInterestImpact(DEFAULT_CONFLICT_INTEREST_IMPACT)
            .relationshipEntityIndividual(DEFAULT_RELATIONSHIP_ENTITY_INDIVIDUAL)
            .affectPermission(DEFAULT_AFFECT_PERMISSION)
            .caseDetails(DEFAULT_CASE_DETAILS)
            .discloseInspector(DEFAULT_DISCLOSE_INSPECTOR)
            .facilityRegisteredInsuranceName(DEFAULT_FACILITY_REGISTERED_INSURANCE_NAME)
            .office(DEFAULT_OFFICE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConflictInterest createUpdatedEntity() {
        return new ConflictInterest()
            .conflictInterestClassification(UPDATED_CONFLICT_INTEREST_CLASSIFICATION)
            .conflictInterestEntityIndividual(UPDATED_CONFLICT_INTEREST_ENTITY_INDIVIDUAL)
            .conflictInterestDateArose(UPDATED_CONFLICT_INTEREST_DATE_AROSE)
            .conflictInterestImpact(UPDATED_CONFLICT_INTEREST_IMPACT)
            .relationshipEntityIndividual(UPDATED_RELATIONSHIP_ENTITY_INDIVIDUAL)
            .affectPermission(UPDATED_AFFECT_PERMISSION)
            .caseDetails(UPDATED_CASE_DETAILS)
            .discloseInspector(UPDATED_DISCLOSE_INSPECTOR)
            .facilityRegisteredInsuranceName(UPDATED_FACILITY_REGISTERED_INSURANCE_NAME)
            .office(UPDATED_OFFICE);
    }

    @BeforeEach
    void initTest() {
        conflictInterest = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedConflictInterest != null) {
            conflictInterestRepository.delete(insertedConflictInterest);
            insertedConflictInterest = null;
        }
    }

    @Test
    @Transactional
    void createConflictInterest() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ConflictInterest
        var returnedConflictInterest = om.readValue(
            restConflictInterestMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(conflictInterest)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ConflictInterest.class
        );

        // Validate the ConflictInterest in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertConflictInterestUpdatableFieldsEquals(returnedConflictInterest, getPersistedConflictInterest(returnedConflictInterest));

        insertedConflictInterest = returnedConflictInterest;
    }

    @Test
    @Transactional
    void createConflictInterestWithExistingId() throws Exception {
        // Create the ConflictInterest with an existing ID
        conflictInterest.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restConflictInterestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(conflictInterest)))
            .andExpect(status().isBadRequest());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllConflictInterests() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        // Get all the conflictInterestList
        restConflictInterestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conflictInterest.getId().intValue())))
            .andExpect(jsonPath("$.[*].conflictInterestClassification").value(hasItem(DEFAULT_CONFLICT_INTEREST_CLASSIFICATION)))
            .andExpect(jsonPath("$.[*].conflictInterestEntityIndividual").value(hasItem(DEFAULT_CONFLICT_INTEREST_ENTITY_INDIVIDUAL)))
            .andExpect(jsonPath("$.[*].conflictInterestDateArose").value(hasItem(DEFAULT_CONFLICT_INTEREST_DATE_AROSE)))
            .andExpect(jsonPath("$.[*].conflictInterestImpact").value(hasItem(DEFAULT_CONFLICT_INTEREST_IMPACT)))
            .andExpect(jsonPath("$.[*].relationshipEntityIndividual").value(hasItem(DEFAULT_RELATIONSHIP_ENTITY_INDIVIDUAL)))
            .andExpect(jsonPath("$.[*].affectPermission").value(hasItem(DEFAULT_AFFECT_PERMISSION)))
            .andExpect(jsonPath("$.[*].caseDetails").value(hasItem(DEFAULT_CASE_DETAILS)))
            .andExpect(jsonPath("$.[*].discloseInspector").value(hasItem(DEFAULT_DISCLOSE_INSPECTOR)))
            .andExpect(jsonPath("$.[*].facilityRegisteredInsuranceName").value(hasItem(DEFAULT_FACILITY_REGISTERED_INSURANCE_NAME)))
            .andExpect(jsonPath("$.[*].office").value(hasItem(DEFAULT_OFFICE)));
    }

    @Test
    @Transactional
    void getConflictInterest() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        // Get the conflictInterest
        restConflictInterestMockMvc
            .perform(get(ENTITY_API_URL_ID, conflictInterest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(conflictInterest.getId().intValue()))
            .andExpect(jsonPath("$.conflictInterestClassification").value(DEFAULT_CONFLICT_INTEREST_CLASSIFICATION))
            .andExpect(jsonPath("$.conflictInterestEntityIndividual").value(DEFAULT_CONFLICT_INTEREST_ENTITY_INDIVIDUAL))
            .andExpect(jsonPath("$.conflictInterestDateArose").value(DEFAULT_CONFLICT_INTEREST_DATE_AROSE))
            .andExpect(jsonPath("$.conflictInterestImpact").value(DEFAULT_CONFLICT_INTEREST_IMPACT))
            .andExpect(jsonPath("$.relationshipEntityIndividual").value(DEFAULT_RELATIONSHIP_ENTITY_INDIVIDUAL))
            .andExpect(jsonPath("$.affectPermission").value(DEFAULT_AFFECT_PERMISSION))
            .andExpect(jsonPath("$.caseDetails").value(DEFAULT_CASE_DETAILS))
            .andExpect(jsonPath("$.discloseInspector").value(DEFAULT_DISCLOSE_INSPECTOR))
            .andExpect(jsonPath("$.facilityRegisteredInsuranceName").value(DEFAULT_FACILITY_REGISTERED_INSURANCE_NAME))
            .andExpect(jsonPath("$.office").value(DEFAULT_OFFICE));
    }

    @Test
    @Transactional
    void getNonExistingConflictInterest() throws Exception {
        // Get the conflictInterest
        restConflictInterestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingConflictInterest() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the conflictInterest
        ConflictInterest updatedConflictInterest = conflictInterestRepository.findById(conflictInterest.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedConflictInterest are not directly saved in db
        em.detach(updatedConflictInterest);
        updatedConflictInterest
            .conflictInterestClassification(UPDATED_CONFLICT_INTEREST_CLASSIFICATION)
            .conflictInterestEntityIndividual(UPDATED_CONFLICT_INTEREST_ENTITY_INDIVIDUAL)
            .conflictInterestDateArose(UPDATED_CONFLICT_INTEREST_DATE_AROSE)
            .conflictInterestImpact(UPDATED_CONFLICT_INTEREST_IMPACT)
            .relationshipEntityIndividual(UPDATED_RELATIONSHIP_ENTITY_INDIVIDUAL)
            .affectPermission(UPDATED_AFFECT_PERMISSION)
            .caseDetails(UPDATED_CASE_DETAILS)
            .discloseInspector(UPDATED_DISCLOSE_INSPECTOR)
            .facilityRegisteredInsuranceName(UPDATED_FACILITY_REGISTERED_INSURANCE_NAME)
            .office(UPDATED_OFFICE);

        restConflictInterestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedConflictInterest.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedConflictInterest))
            )
            .andExpect(status().isOk());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedConflictInterestToMatchAllProperties(updatedConflictInterest);
    }

    @Test
    @Transactional
    void putNonExistingConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, conflictInterest.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(conflictInterest))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(conflictInterest))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(conflictInterest)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateConflictInterestWithPatch() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the conflictInterest using partial update
        ConflictInterest partialUpdatedConflictInterest = new ConflictInterest();
        partialUpdatedConflictInterest.setId(conflictInterest.getId());

        partialUpdatedConflictInterest
            .conflictInterestImpact(UPDATED_CONFLICT_INTEREST_IMPACT)
            .affectPermission(UPDATED_AFFECT_PERMISSION)
            .caseDetails(UPDATED_CASE_DETAILS)
            .facilityRegisteredInsuranceName(UPDATED_FACILITY_REGISTERED_INSURANCE_NAME)
            .office(UPDATED_OFFICE);

        restConflictInterestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedConflictInterest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedConflictInterest))
            )
            .andExpect(status().isOk());

        // Validate the ConflictInterest in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertConflictInterestUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedConflictInterest, conflictInterest),
            getPersistedConflictInterest(conflictInterest)
        );
    }

    @Test
    @Transactional
    void fullUpdateConflictInterestWithPatch() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the conflictInterest using partial update
        ConflictInterest partialUpdatedConflictInterest = new ConflictInterest();
        partialUpdatedConflictInterest.setId(conflictInterest.getId());

        partialUpdatedConflictInterest
            .conflictInterestClassification(UPDATED_CONFLICT_INTEREST_CLASSIFICATION)
            .conflictInterestEntityIndividual(UPDATED_CONFLICT_INTEREST_ENTITY_INDIVIDUAL)
            .conflictInterestDateArose(UPDATED_CONFLICT_INTEREST_DATE_AROSE)
            .conflictInterestImpact(UPDATED_CONFLICT_INTEREST_IMPACT)
            .relationshipEntityIndividual(UPDATED_RELATIONSHIP_ENTITY_INDIVIDUAL)
            .affectPermission(UPDATED_AFFECT_PERMISSION)
            .caseDetails(UPDATED_CASE_DETAILS)
            .discloseInspector(UPDATED_DISCLOSE_INSPECTOR)
            .facilityRegisteredInsuranceName(UPDATED_FACILITY_REGISTERED_INSURANCE_NAME)
            .office(UPDATED_OFFICE);

        restConflictInterestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedConflictInterest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedConflictInterest))
            )
            .andExpect(status().isOk());

        // Validate the ConflictInterest in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertConflictInterestUpdatableFieldsEquals(
            partialUpdatedConflictInterest,
            getPersistedConflictInterest(partialUpdatedConflictInterest)
        );
    }

    @Test
    @Transactional
    void patchNonExistingConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, conflictInterest.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(conflictInterest))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(conflictInterest))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamConflictInterest() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        conflictInterest.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConflictInterestMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(conflictInterest)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ConflictInterest in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteConflictInterest() throws Exception {
        // Initialize the database
        insertedConflictInterest = conflictInterestRepository.saveAndFlush(conflictInterest);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the conflictInterest
        restConflictInterestMockMvc
            .perform(delete(ENTITY_API_URL_ID, conflictInterest.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return conflictInterestRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ConflictInterest getPersistedConflictInterest(ConflictInterest conflictInterest) {
        return conflictInterestRepository.findById(conflictInterest.getId()).orElseThrow();
    }

    protected void assertPersistedConflictInterestToMatchAllProperties(ConflictInterest expectedConflictInterest) {
        assertConflictInterestAllPropertiesEquals(expectedConflictInterest, getPersistedConflictInterest(expectedConflictInterest));
    }

    protected void assertPersistedConflictInterestToMatchUpdatableProperties(ConflictInterest expectedConflictInterest) {
        assertConflictInterestAllUpdatablePropertiesEquals(
            expectedConflictInterest,
            getPersistedConflictInterest(expectedConflictInterest)
        );
    }
}
