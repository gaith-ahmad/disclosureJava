package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.DisclosureAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.Disclosure;
import com.gosi.disclosure.repository.DisclosureRepository;
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
 * Integration tests for the {@link DisclosureResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DisclosureResourceIT {

    private static final String DEFAULT_PURPOSE_OF_DISCLOSURE = "AAAAAAAAAA";
    private static final String UPDATED_PURPOSE_OF_DISCLOSURE = "BBBBBBBBBB";

    private static final String DEFAULT_CREATION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EXT = "AAAAAAAAAA";
    private static final String UPDATED_EXT = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLIC_ADMINISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_PUBLIC_ADMINISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIRM = "AAAAAAAAAA";
    private static final String UPDATED_CONFIRM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_NAME_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_NUMBER_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_NUMBER_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_TITLE_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_JOB_TITLE_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_EXT_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_EXT_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ARE_THERE_RELATIVES = false;
    private static final Boolean UPDATED_ARE_THERE_RELATIVES = true;

    private static final String DEFAULT_FILE = "AAAAAAAAAA";
    private static final String UPDATED_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_FILENAME = "AAAAAAAAAA";
    private static final String UPDATED_FILENAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/disclosures";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DisclosureRepository disclosureRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDisclosureMockMvc;

    private Disclosure disclosure;

    private Disclosure insertedDisclosure;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disclosure createEntity() {
        return new Disclosure()
            .purposeOfDisclosure(DEFAULT_PURPOSE_OF_DISCLOSURE)
            .creationDate(DEFAULT_CREATION_DATE)
            .name(DEFAULT_NAME)
            .jobTitle(DEFAULT_JOB_TITLE)
            .jobNumber(DEFAULT_JOB_NUMBER)
            .ext(DEFAULT_EXT)
            .publicAdministration(DEFAULT_PUBLIC_ADMINISTRATION)
            .administration(DEFAULT_ADMINISTRATION)
            .confirm(DEFAULT_CONFIRM)
            .email(DEFAULT_EMAIL)
            .emailDirectManager(DEFAULT_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(DEFAULT_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(DEFAULT_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(DEFAULT_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(DEFAULT_EXT_DIRECT_MANAGER)
            .areThereRelatives(DEFAULT_ARE_THERE_RELATIVES)
            .file(DEFAULT_FILE)
            .filename(DEFAULT_FILENAME);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disclosure createUpdatedEntity() {
        return new Disclosure()
            .purposeOfDisclosure(UPDATED_PURPOSE_OF_DISCLOSURE)
            .creationDate(UPDATED_CREATION_DATE)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .confirm(UPDATED_CONFIRM)
            .email(UPDATED_EMAIL)
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER)
            .areThereRelatives(UPDATED_ARE_THERE_RELATIVES)
            .file(UPDATED_FILE)
            .filename(UPDATED_FILENAME);
    }

    @BeforeEach
    void initTest() {
        disclosure = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedDisclosure != null) {
            disclosureRepository.delete(insertedDisclosure);
            insertedDisclosure = null;
        }
    }

    @Test
    @Transactional
    void createDisclosure() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Disclosure
        var returnedDisclosure = om.readValue(
            restDisclosureMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(disclosure)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Disclosure.class
        );

        // Validate the Disclosure in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDisclosureUpdatableFieldsEquals(returnedDisclosure, getPersistedDisclosure(returnedDisclosure));

        insertedDisclosure = returnedDisclosure;
    }

    @Test
    @Transactional
    void createDisclosureWithExistingId() throws Exception {
        // Create the Disclosure with an existing ID
        disclosure.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisclosureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(disclosure)))
            .andExpect(status().isBadRequest());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkAreThereRelativesIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        disclosure.setAreThereRelatives(null);

        // Create the Disclosure, which fails.

        restDisclosureMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(disclosure)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDisclosures() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        // Get all the disclosureList
        restDisclosureMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disclosure.getId().intValue())))
            .andExpect(jsonPath("$.[*].purposeOfDisclosure").value(hasItem(DEFAULT_PURPOSE_OF_DISCLOSURE)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].jobTitle").value(hasItem(DEFAULT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].jobNumber").value(hasItem(DEFAULT_JOB_NUMBER)))
            .andExpect(jsonPath("$.[*].ext").value(hasItem(DEFAULT_EXT)))
            .andExpect(jsonPath("$.[*].publicAdministration").value(hasItem(DEFAULT_PUBLIC_ADMINISTRATION)))
            .andExpect(jsonPath("$.[*].administration").value(hasItem(DEFAULT_ADMINISTRATION)))
            .andExpect(jsonPath("$.[*].confirm").value(hasItem(DEFAULT_CONFIRM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].emailDirectManager").value(hasItem(DEFAULT_EMAIL_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].nameDirectManager").value(hasItem(DEFAULT_NAME_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].jobNumberDirectManager").value(hasItem(DEFAULT_JOB_NUMBER_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].jobTitleDirectManager").value(hasItem(DEFAULT_JOB_TITLE_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].extDirectManager").value(hasItem(DEFAULT_EXT_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].areThereRelatives").value(hasItem(DEFAULT_ARE_THERE_RELATIVES)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(DEFAULT_FILE)))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME)));
    }

    @Test
    @Transactional
    void getDisclosure() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        // Get the disclosure
        restDisclosureMockMvc
            .perform(get(ENTITY_API_URL_ID, disclosure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(disclosure.getId().intValue()))
            .andExpect(jsonPath("$.purposeOfDisclosure").value(DEFAULT_PURPOSE_OF_DISCLOSURE))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.jobTitle").value(DEFAULT_JOB_TITLE))
            .andExpect(jsonPath("$.jobNumber").value(DEFAULT_JOB_NUMBER))
            .andExpect(jsonPath("$.ext").value(DEFAULT_EXT))
            .andExpect(jsonPath("$.publicAdministration").value(DEFAULT_PUBLIC_ADMINISTRATION))
            .andExpect(jsonPath("$.administration").value(DEFAULT_ADMINISTRATION))
            .andExpect(jsonPath("$.confirm").value(DEFAULT_CONFIRM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.emailDirectManager").value(DEFAULT_EMAIL_DIRECT_MANAGER))
            .andExpect(jsonPath("$.nameDirectManager").value(DEFAULT_NAME_DIRECT_MANAGER))
            .andExpect(jsonPath("$.jobNumberDirectManager").value(DEFAULT_JOB_NUMBER_DIRECT_MANAGER))
            .andExpect(jsonPath("$.jobTitleDirectManager").value(DEFAULT_JOB_TITLE_DIRECT_MANAGER))
            .andExpect(jsonPath("$.extDirectManager").value(DEFAULT_EXT_DIRECT_MANAGER))
            .andExpect(jsonPath("$.areThereRelatives").value(DEFAULT_ARE_THERE_RELATIVES))
            .andExpect(jsonPath("$.file").value(DEFAULT_FILE))
            .andExpect(jsonPath("$.filename").value(DEFAULT_FILENAME));
    }

    @Test
    @Transactional
    void getNonExistingDisclosure() throws Exception {
        // Get the disclosure
        restDisclosureMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDisclosure() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the disclosure
        Disclosure updatedDisclosure = disclosureRepository.findById(disclosure.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDisclosure are not directly saved in db
        em.detach(updatedDisclosure);
        updatedDisclosure
            .purposeOfDisclosure(UPDATED_PURPOSE_OF_DISCLOSURE)
            .creationDate(UPDATED_CREATION_DATE)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .confirm(UPDATED_CONFIRM)
            .email(UPDATED_EMAIL)
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER)
            .areThereRelatives(UPDATED_ARE_THERE_RELATIVES)
            .file(UPDATED_FILE)
            .filename(UPDATED_FILENAME);

        restDisclosureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDisclosure.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedDisclosure))
            )
            .andExpect(status().isOk());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDisclosureToMatchAllProperties(updatedDisclosure);
    }

    @Test
    @Transactional
    void putNonExistingDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, disclosure.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(disclosure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(disclosure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(disclosure)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDisclosureWithPatch() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the disclosure using partial update
        Disclosure partialUpdatedDisclosure = new Disclosure();
        partialUpdatedDisclosure.setId(disclosure.getId());

        partialUpdatedDisclosure
            .purposeOfDisclosure(UPDATED_PURPOSE_OF_DISCLOSURE)
            .creationDate(UPDATED_CREATION_DATE)
            .name(UPDATED_NAME)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .administration(UPDATED_ADMINISTRATION)
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .areThereRelatives(UPDATED_ARE_THERE_RELATIVES)
            .file(UPDATED_FILE);

        restDisclosureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisclosure.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDisclosure))
            )
            .andExpect(status().isOk());

        // Validate the Disclosure in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDisclosureUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDisclosure, disclosure),
            getPersistedDisclosure(disclosure)
        );
    }

    @Test
    @Transactional
    void fullUpdateDisclosureWithPatch() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the disclosure using partial update
        Disclosure partialUpdatedDisclosure = new Disclosure();
        partialUpdatedDisclosure.setId(disclosure.getId());

        partialUpdatedDisclosure
            .purposeOfDisclosure(UPDATED_PURPOSE_OF_DISCLOSURE)
            .creationDate(UPDATED_CREATION_DATE)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .confirm(UPDATED_CONFIRM)
            .email(UPDATED_EMAIL)
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER)
            .areThereRelatives(UPDATED_ARE_THERE_RELATIVES)
            .file(UPDATED_FILE)
            .filename(UPDATED_FILENAME);

        restDisclosureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisclosure.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDisclosure))
            )
            .andExpect(status().isOk());

        // Validate the Disclosure in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDisclosureUpdatableFieldsEquals(partialUpdatedDisclosure, getPersistedDisclosure(partialUpdatedDisclosure));
    }

    @Test
    @Transactional
    void patchNonExistingDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, disclosure.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(disclosure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(disclosure))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDisclosure() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        disclosure.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisclosureMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(disclosure)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Disclosure in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDisclosure() throws Exception {
        // Initialize the database
        insertedDisclosure = disclosureRepository.saveAndFlush(disclosure);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the disclosure
        restDisclosureMockMvc
            .perform(delete(ENTITY_API_URL_ID, disclosure.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return disclosureRepository.count();
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

    protected Disclosure getPersistedDisclosure(Disclosure disclosure) {
        return disclosureRepository.findById(disclosure.getId()).orElseThrow();
    }

    protected void assertPersistedDisclosureToMatchAllProperties(Disclosure expectedDisclosure) {
        assertDisclosureAllPropertiesEquals(expectedDisclosure, getPersistedDisclosure(expectedDisclosure));
    }

    protected void assertPersistedDisclosureToMatchUpdatableProperties(Disclosure expectedDisclosure) {
        assertDisclosureAllUpdatablePropertiesEquals(expectedDisclosure, getPersistedDisclosure(expectedDisclosure));
    }
}
