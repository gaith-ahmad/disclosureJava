package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.RelativesAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.Relatives;
import com.gosi.disclosure.repository.RelativesRepository;
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
 * Integration tests for the {@link RelativesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RelativesResourceIT {

    private static final String DEFAULT_NAME_DISCLOSER = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DISCLOSER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_RELATIVE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_RELATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_TITLE_RELATIVE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE_RELATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIVE_JOB_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RELATIVE_JOB_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_RELATIVE = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_RELATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIVE_EXTENSION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RELATIVE_EXTENSION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RELATIVE_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIVE_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_GENERAL_ADMINISTRATION_RELATIVE = "AAAAAAAAAA";
    private static final String UPDATED_GENERAL_ADMINISTRATION_RELATIVE = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATION_RELATIVE = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATION_RELATIVE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/relatives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RelativesRepository relativesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelativesMockMvc;

    private Relatives relatives;

    private Relatives insertedRelatives;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Relatives createEntity() {
        return new Relatives()
            .nameDiscloser(DEFAULT_NAME_DISCLOSER)
            .nameRelative(DEFAULT_NAME_RELATIVE)
            .jobTitleRelative(DEFAULT_JOB_TITLE_RELATIVE)
            .relativeJobNumber(DEFAULT_RELATIVE_JOB_NUMBER)
            .emailRelative(DEFAULT_EMAIL_RELATIVE)
            .relativeExtensionNumber(DEFAULT_RELATIVE_EXTENSION_NUMBER)
            .relativeRelationship(DEFAULT_RELATIVE_RELATIONSHIP)
            .generalAdministrationRelative(DEFAULT_GENERAL_ADMINISTRATION_RELATIVE)
            .administrationRelative(DEFAULT_ADMINISTRATION_RELATIVE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Relatives createUpdatedEntity() {
        return new Relatives()
            .nameDiscloser(UPDATED_NAME_DISCLOSER)
            .nameRelative(UPDATED_NAME_RELATIVE)
            .jobTitleRelative(UPDATED_JOB_TITLE_RELATIVE)
            .relativeJobNumber(UPDATED_RELATIVE_JOB_NUMBER)
            .emailRelative(UPDATED_EMAIL_RELATIVE)
            .relativeExtensionNumber(UPDATED_RELATIVE_EXTENSION_NUMBER)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .generalAdministrationRelative(UPDATED_GENERAL_ADMINISTRATION_RELATIVE)
            .administrationRelative(UPDATED_ADMINISTRATION_RELATIVE);
    }

    @BeforeEach
    void initTest() {
        relatives = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedRelatives != null) {
            relativesRepository.delete(insertedRelatives);
            insertedRelatives = null;
        }
    }

    @Test
    @Transactional
    void createRelatives() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Relatives
        var returnedRelatives = om.readValue(
            restRelativesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Relatives.class
        );

        // Validate the Relatives in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRelativesUpdatableFieldsEquals(returnedRelatives, getPersistedRelatives(returnedRelatives));

        insertedRelatives = returnedRelatives;
    }

    @Test
    @Transactional
    void createRelativesWithExistingId() throws Exception {
        // Create the Relatives with an existing ID
        relatives.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameDiscloserIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setNameDiscloser(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameRelativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setNameRelative(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJobTitleRelativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setJobTitleRelative(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRelativeJobNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setRelativeJobNumber(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailRelativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setEmailRelative(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkRelativeExtensionNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setRelativeExtensionNumber(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGeneralAdministrationRelativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setGeneralAdministrationRelative(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAdministrationRelativeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        relatives.setAdministrationRelative(null);

        // Create the Relatives, which fails.

        restRelativesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRelatives() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        // Get all the relativesList
        restRelativesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatives.getId().intValue())))
            .andExpect(jsonPath("$.[*].nameDiscloser").value(hasItem(DEFAULT_NAME_DISCLOSER)))
            .andExpect(jsonPath("$.[*].nameRelative").value(hasItem(DEFAULT_NAME_RELATIVE)))
            .andExpect(jsonPath("$.[*].jobTitleRelative").value(hasItem(DEFAULT_JOB_TITLE_RELATIVE)))
            .andExpect(jsonPath("$.[*].relativeJobNumber").value(hasItem(DEFAULT_RELATIVE_JOB_NUMBER)))
            .andExpect(jsonPath("$.[*].emailRelative").value(hasItem(DEFAULT_EMAIL_RELATIVE)))
            .andExpect(jsonPath("$.[*].relativeExtensionNumber").value(hasItem(DEFAULT_RELATIVE_EXTENSION_NUMBER)))
            .andExpect(jsonPath("$.[*].relativeRelationship").value(hasItem(DEFAULT_RELATIVE_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].generalAdministrationRelative").value(hasItem(DEFAULT_GENERAL_ADMINISTRATION_RELATIVE)))
            .andExpect(jsonPath("$.[*].administrationRelative").value(hasItem(DEFAULT_ADMINISTRATION_RELATIVE)));
    }

    @Test
    @Transactional
    void getRelatives() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        // Get the relatives
        restRelativesMockMvc
            .perform(get(ENTITY_API_URL_ID, relatives.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relatives.getId().intValue()))
            .andExpect(jsonPath("$.nameDiscloser").value(DEFAULT_NAME_DISCLOSER))
            .andExpect(jsonPath("$.nameRelative").value(DEFAULT_NAME_RELATIVE))
            .andExpect(jsonPath("$.jobTitleRelative").value(DEFAULT_JOB_TITLE_RELATIVE))
            .andExpect(jsonPath("$.relativeJobNumber").value(DEFAULT_RELATIVE_JOB_NUMBER))
            .andExpect(jsonPath("$.emailRelative").value(DEFAULT_EMAIL_RELATIVE))
            .andExpect(jsonPath("$.relativeExtensionNumber").value(DEFAULT_RELATIVE_EXTENSION_NUMBER))
            .andExpect(jsonPath("$.relativeRelationship").value(DEFAULT_RELATIVE_RELATIONSHIP))
            .andExpect(jsonPath("$.generalAdministrationRelative").value(DEFAULT_GENERAL_ADMINISTRATION_RELATIVE))
            .andExpect(jsonPath("$.administrationRelative").value(DEFAULT_ADMINISTRATION_RELATIVE));
    }

    @Test
    @Transactional
    void getNonExistingRelatives() throws Exception {
        // Get the relatives
        restRelativesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRelatives() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the relatives
        Relatives updatedRelatives = relativesRepository.findById(relatives.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRelatives are not directly saved in db
        em.detach(updatedRelatives);
        updatedRelatives
            .nameDiscloser(UPDATED_NAME_DISCLOSER)
            .nameRelative(UPDATED_NAME_RELATIVE)
            .jobTitleRelative(UPDATED_JOB_TITLE_RELATIVE)
            .relativeJobNumber(UPDATED_RELATIVE_JOB_NUMBER)
            .emailRelative(UPDATED_EMAIL_RELATIVE)
            .relativeExtensionNumber(UPDATED_RELATIVE_EXTENSION_NUMBER)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .generalAdministrationRelative(UPDATED_GENERAL_ADMINISTRATION_RELATIVE)
            .administrationRelative(UPDATED_ADMINISTRATION_RELATIVE);

        restRelativesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelatives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRelatives))
            )
            .andExpect(status().isOk());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRelativesToMatchAllProperties(updatedRelatives);
    }

    @Test
    @Transactional
    void putNonExistingRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, relatives.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(relatives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRelativesWithPatch() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the relatives using partial update
        Relatives partialUpdatedRelatives = new Relatives();
        partialUpdatedRelatives.setId(relatives.getId());

        partialUpdatedRelatives
            .nameRelative(UPDATED_NAME_RELATIVE)
            .jobTitleRelative(UPDATED_JOB_TITLE_RELATIVE)
            .relativeJobNumber(UPDATED_RELATIVE_JOB_NUMBER)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP);

        restRelativesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelatives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRelatives))
            )
            .andExpect(status().isOk());

        // Validate the Relatives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRelativesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRelatives, relatives),
            getPersistedRelatives(relatives)
        );
    }

    @Test
    @Transactional
    void fullUpdateRelativesWithPatch() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the relatives using partial update
        Relatives partialUpdatedRelatives = new Relatives();
        partialUpdatedRelatives.setId(relatives.getId());

        partialUpdatedRelatives
            .nameDiscloser(UPDATED_NAME_DISCLOSER)
            .nameRelative(UPDATED_NAME_RELATIVE)
            .jobTitleRelative(UPDATED_JOB_TITLE_RELATIVE)
            .relativeJobNumber(UPDATED_RELATIVE_JOB_NUMBER)
            .emailRelative(UPDATED_EMAIL_RELATIVE)
            .relativeExtensionNumber(UPDATED_RELATIVE_EXTENSION_NUMBER)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .generalAdministrationRelative(UPDATED_GENERAL_ADMINISTRATION_RELATIVE)
            .administrationRelative(UPDATED_ADMINISTRATION_RELATIVE);

        restRelativesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelatives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRelatives))
            )
            .andExpect(status().isOk());

        // Validate the Relatives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRelativesUpdatableFieldsEquals(partialUpdatedRelatives, getPersistedRelatives(partialUpdatedRelatives));
    }

    @Test
    @Transactional
    void patchNonExistingRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, relatives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(relatives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(relatives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelatives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        relatives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelativesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(relatives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Relatives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelatives() throws Exception {
        // Initialize the database
        insertedRelatives = relativesRepository.saveAndFlush(relatives);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the relatives
        restRelativesMockMvc
            .perform(delete(ENTITY_API_URL_ID, relatives.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return relativesRepository.count();
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

    protected Relatives getPersistedRelatives(Relatives relatives) {
        return relativesRepository.findById(relatives.getId()).orElseThrow();
    }

    protected void assertPersistedRelativesToMatchAllProperties(Relatives expectedRelatives) {
        assertRelativesAllPropertiesEquals(expectedRelatives, getPersistedRelatives(expectedRelatives));
    }

    protected void assertPersistedRelativesToMatchUpdatableProperties(Relatives expectedRelatives) {
        assertRelativesAllUpdatablePropertiesEquals(expectedRelatives, getPersistedRelatives(expectedRelatives));
    }
}
