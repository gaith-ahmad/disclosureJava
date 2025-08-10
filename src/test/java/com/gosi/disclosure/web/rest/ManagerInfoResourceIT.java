package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.ManagerInfoAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.ManagerInfo;
import com.gosi.disclosure.repository.ManagerInfoRepository;
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
 * Integration tests for the {@link ManagerInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ManagerInfoResourceIT {

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

    private static final String ENTITY_API_URL = "/api/manager-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ManagerInfoRepository managerInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManagerInfoMockMvc;

    private ManagerInfo managerInfo;

    private ManagerInfo insertedManagerInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagerInfo createEntity() {
        return new ManagerInfo()
            .emailDirectManager(DEFAULT_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(DEFAULT_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(DEFAULT_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(DEFAULT_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(DEFAULT_EXT_DIRECT_MANAGER);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagerInfo createUpdatedEntity() {
        return new ManagerInfo()
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER);
    }

    @BeforeEach
    void initTest() {
        managerInfo = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedManagerInfo != null) {
            managerInfoRepository.delete(insertedManagerInfo);
            insertedManagerInfo = null;
        }
    }

    @Test
    @Transactional
    void createManagerInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ManagerInfo
        var returnedManagerInfo = om.readValue(
            restManagerInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ManagerInfo.class
        );

        // Validate the ManagerInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertManagerInfoUpdatableFieldsEquals(returnedManagerInfo, getPersistedManagerInfo(returnedManagerInfo));

        insertedManagerInfo = returnedManagerInfo;
    }

    @Test
    @Transactional
    void createManagerInfoWithExistingId() throws Exception {
        // Create the ManagerInfo with an existing ID
        managerInfo.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEmailDirectManagerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        managerInfo.setEmailDirectManager(null);

        // Create the ManagerInfo, which fails.

        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameDirectManagerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        managerInfo.setNameDirectManager(null);

        // Create the ManagerInfo, which fails.

        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJobNumberDirectManagerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        managerInfo.setJobNumberDirectManager(null);

        // Create the ManagerInfo, which fails.

        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJobTitleDirectManagerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        managerInfo.setJobTitleDirectManager(null);

        // Create the ManagerInfo, which fails.

        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtDirectManagerIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        managerInfo.setExtDirectManager(null);

        // Create the ManagerInfo, which fails.

        restManagerInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllManagerInfos() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        // Get all the managerInfoList
        restManagerInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(managerInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].emailDirectManager").value(hasItem(DEFAULT_EMAIL_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].nameDirectManager").value(hasItem(DEFAULT_NAME_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].jobNumberDirectManager").value(hasItem(DEFAULT_JOB_NUMBER_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].jobTitleDirectManager").value(hasItem(DEFAULT_JOB_TITLE_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].extDirectManager").value(hasItem(DEFAULT_EXT_DIRECT_MANAGER)));
    }

    @Test
    @Transactional
    void getManagerInfo() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        // Get the managerInfo
        restManagerInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, managerInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(managerInfo.getId().intValue()))
            .andExpect(jsonPath("$.emailDirectManager").value(DEFAULT_EMAIL_DIRECT_MANAGER))
            .andExpect(jsonPath("$.nameDirectManager").value(DEFAULT_NAME_DIRECT_MANAGER))
            .andExpect(jsonPath("$.jobNumberDirectManager").value(DEFAULT_JOB_NUMBER_DIRECT_MANAGER))
            .andExpect(jsonPath("$.jobTitleDirectManager").value(DEFAULT_JOB_TITLE_DIRECT_MANAGER))
            .andExpect(jsonPath("$.extDirectManager").value(DEFAULT_EXT_DIRECT_MANAGER));
    }

    @Test
    @Transactional
    void getNonExistingManagerInfo() throws Exception {
        // Get the managerInfo
        restManagerInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingManagerInfo() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managerInfo
        ManagerInfo updatedManagerInfo = managerInfoRepository.findById(managerInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedManagerInfo are not directly saved in db
        em.detach(updatedManagerInfo);
        updatedManagerInfo
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER);

        restManagerInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedManagerInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedManagerInfo))
            )
            .andExpect(status().isOk());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedManagerInfoToMatchAllProperties(updatedManagerInfo);
    }

    @Test
    @Transactional
    void putNonExistingManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, managerInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(managerInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(managerInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateManagerInfoWithPatch() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managerInfo using partial update
        ManagerInfo partialUpdatedManagerInfo = new ManagerInfo();
        partialUpdatedManagerInfo.setId(managerInfo.getId());

        partialUpdatedManagerInfo
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER);

        restManagerInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManagerInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedManagerInfo))
            )
            .andExpect(status().isOk());

        // Validate the ManagerInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertManagerInfoUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedManagerInfo, managerInfo),
            getPersistedManagerInfo(managerInfo)
        );
    }

    @Test
    @Transactional
    void fullUpdateManagerInfoWithPatch() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managerInfo using partial update
        ManagerInfo partialUpdatedManagerInfo = new ManagerInfo();
        partialUpdatedManagerInfo.setId(managerInfo.getId());

        partialUpdatedManagerInfo
            .emailDirectManager(UPDATED_EMAIL_DIRECT_MANAGER)
            .nameDirectManager(UPDATED_NAME_DIRECT_MANAGER)
            .jobNumberDirectManager(UPDATED_JOB_NUMBER_DIRECT_MANAGER)
            .jobTitleDirectManager(UPDATED_JOB_TITLE_DIRECT_MANAGER)
            .extDirectManager(UPDATED_EXT_DIRECT_MANAGER);

        restManagerInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManagerInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedManagerInfo))
            )
            .andExpect(status().isOk());

        // Validate the ManagerInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertManagerInfoUpdatableFieldsEquals(partialUpdatedManagerInfo, getPersistedManagerInfo(partialUpdatedManagerInfo));
    }

    @Test
    @Transactional
    void patchNonExistingManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, managerInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(managerInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(managerInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamManagerInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managerInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagerInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(managerInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManagerInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteManagerInfo() throws Exception {
        // Initialize the database
        insertedManagerInfo = managerInfoRepository.saveAndFlush(managerInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the managerInfo
        restManagerInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, managerInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return managerInfoRepository.count();
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

    protected ManagerInfo getPersistedManagerInfo(ManagerInfo managerInfo) {
        return managerInfoRepository.findById(managerInfo.getId()).orElseThrow();
    }

    protected void assertPersistedManagerInfoToMatchAllProperties(ManagerInfo expectedManagerInfo) {
        assertManagerInfoAllPropertiesEquals(expectedManagerInfo, getPersistedManagerInfo(expectedManagerInfo));
    }

    protected void assertPersistedManagerInfoToMatchUpdatableProperties(ManagerInfo expectedManagerInfo) {
        assertManagerInfoAllUpdatablePropertiesEquals(expectedManagerInfo, getPersistedManagerInfo(expectedManagerInfo));
    }
}
