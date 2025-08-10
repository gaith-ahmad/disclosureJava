package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.UserInfoAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.UserInfo;
import com.gosi.disclosure.repository.UserInfoRepository;
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
 * Integration tests for the {@link UserInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserInfoResourceIT {

    private static final String DEFAULT_LOGIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_KEY = 1L;
    private static final Long UPDATED_KEY = 2L;

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

    private static final String DEFAULT_RELATIVE_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_RELATIVE_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserInfoMockMvc;

    private UserInfo userInfo;

    private UserInfo insertedUserInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfo createEntity() {
        return new UserInfo()
            .loginName(DEFAULT_LOGIN_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .email(DEFAULT_EMAIL)
            .key(DEFAULT_KEY)
            .name(DEFAULT_NAME)
            .jobTitle(DEFAULT_JOB_TITLE)
            .jobNumber(DEFAULT_JOB_NUMBER)
            .ext(DEFAULT_EXT)
            .publicAdministration(DEFAULT_PUBLIC_ADMINISTRATION)
            .administration(DEFAULT_ADMINISTRATION)
            .relativeRelationship(DEFAULT_RELATIVE_RELATIONSHIP)
            .office(DEFAULT_OFFICE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfo createUpdatedEntity() {
        return new UserInfo()
            .loginName(UPDATED_LOGIN_NAME)
            .fullName(UPDATED_FULL_NAME)
            .email(UPDATED_EMAIL)
            .key(UPDATED_KEY)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .office(UPDATED_OFFICE);
    }

    @BeforeEach
    void initTest() {
        userInfo = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedUserInfo != null) {
            userInfoRepository.delete(insertedUserInfo);
            insertedUserInfo = null;
        }
    }

    @Test
    @Transactional
    void createUserInfo() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UserInfo
        var returnedUserInfo = om.readValue(
            restUserInfoMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UserInfo.class
        );

        // Validate the UserInfo in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUserInfoUpdatableFieldsEquals(returnedUserInfo, getPersistedUserInfo(returnedUserInfo));

        insertedUserInfo = returnedUserInfo;
    }

    @Test
    @Transactional
    void createUserInfoWithExistingId() throws Exception {
        // Create the UserInfo with an existing ID
        userInfo.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkLoginNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setLoginName(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFullNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setFullName(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setEmail(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setName(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJobTitleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setJobTitle(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkJobNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setJobNumber(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkExtIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setExt(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPublicAdministrationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setPublicAdministration(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAdministrationIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        userInfo.setAdministration(null);

        // Create the UserInfo, which fails.

        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUserInfos() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        // Get all the userInfoList
        restUserInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].loginName").value(hasItem(DEFAULT_LOGIN_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].jobTitle").value(hasItem(DEFAULT_JOB_TITLE)))
            .andExpect(jsonPath("$.[*].jobNumber").value(hasItem(DEFAULT_JOB_NUMBER)))
            .andExpect(jsonPath("$.[*].ext").value(hasItem(DEFAULT_EXT)))
            .andExpect(jsonPath("$.[*].publicAdministration").value(hasItem(DEFAULT_PUBLIC_ADMINISTRATION)))
            .andExpect(jsonPath("$.[*].administration").value(hasItem(DEFAULT_ADMINISTRATION)))
            .andExpect(jsonPath("$.[*].relativeRelationship").value(hasItem(DEFAULT_RELATIVE_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].office").value(hasItem(DEFAULT_OFFICE)));
    }

    @Test
    @Transactional
    void getUserInfo() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        // Get the userInfo
        restUserInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, userInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userInfo.getId().intValue()))
            .andExpect(jsonPath("$.loginName").value(DEFAULT_LOGIN_NAME))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.jobTitle").value(DEFAULT_JOB_TITLE))
            .andExpect(jsonPath("$.jobNumber").value(DEFAULT_JOB_NUMBER))
            .andExpect(jsonPath("$.ext").value(DEFAULT_EXT))
            .andExpect(jsonPath("$.publicAdministration").value(DEFAULT_PUBLIC_ADMINISTRATION))
            .andExpect(jsonPath("$.administration").value(DEFAULT_ADMINISTRATION))
            .andExpect(jsonPath("$.relativeRelationship").value(DEFAULT_RELATIVE_RELATIONSHIP))
            .andExpect(jsonPath("$.office").value(DEFAULT_OFFICE));
    }

    @Test
    @Transactional
    void getNonExistingUserInfo() throws Exception {
        // Get the userInfo
        restUserInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUserInfo() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userInfo
        UserInfo updatedUserInfo = userInfoRepository.findById(userInfo.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUserInfo are not directly saved in db
        em.detach(updatedUserInfo);
        updatedUserInfo
            .loginName(UPDATED_LOGIN_NAME)
            .fullName(UPDATED_FULL_NAME)
            .email(UPDATED_EMAIL)
            .key(UPDATED_KEY)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .office(UPDATED_OFFICE);

        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUserInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUserInfoToMatchAllProperties(updatedUserInfo);
    }

    @Test
    @Transactional
    void putNonExistingUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userInfo.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserInfoWithPatch() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userInfo using partial update
        UserInfo partialUpdatedUserInfo = new UserInfo();
        partialUpdatedUserInfo.setId(userInfo.getId());

        partialUpdatedUserInfo
            .loginName(UPDATED_LOGIN_NAME)
            .name(UPDATED_NAME)
            .jobNumber(UPDATED_JOB_NUMBER)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .office(UPDATED_OFFICE);

        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUserInfoUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedUserInfo, userInfo), getPersistedUserInfo(userInfo));
    }

    @Test
    @Transactional
    void fullUpdateUserInfoWithPatch() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the userInfo using partial update
        UserInfo partialUpdatedUserInfo = new UserInfo();
        partialUpdatedUserInfo.setId(userInfo.getId());

        partialUpdatedUserInfo
            .loginName(UPDATED_LOGIN_NAME)
            .fullName(UPDATED_FULL_NAME)
            .email(UPDATED_EMAIL)
            .key(UPDATED_KEY)
            .name(UPDATED_NAME)
            .jobTitle(UPDATED_JOB_TITLE)
            .jobNumber(UPDATED_JOB_NUMBER)
            .ext(UPDATED_EXT)
            .publicAdministration(UPDATED_PUBLIC_ADMINISTRATION)
            .administration(UPDATED_ADMINISTRATION)
            .relativeRelationship(UPDATED_RELATIVE_RELATIONSHIP)
            .office(UPDATED_OFFICE);

        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUserInfoUpdatableFieldsEquals(partialUpdatedUserInfo, getPersistedUserInfo(partialUpdatedUserInfo));
    }

    @Test
    @Transactional
    void patchNonExistingUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserInfo() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        userInfo.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(userInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfo in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserInfo() throws Exception {
        // Initialize the database
        insertedUserInfo = userInfoRepository.saveAndFlush(userInfo);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the userInfo
        restUserInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, userInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return userInfoRepository.count();
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

    protected UserInfo getPersistedUserInfo(UserInfo userInfo) {
        return userInfoRepository.findById(userInfo.getId()).orElseThrow();
    }

    protected void assertPersistedUserInfoToMatchAllProperties(UserInfo expectedUserInfo) {
        assertUserInfoAllPropertiesEquals(expectedUserInfo, getPersistedUserInfo(expectedUserInfo));
    }

    protected void assertPersistedUserInfoToMatchUpdatableProperties(UserInfo expectedUserInfo) {
        assertUserInfoAllUpdatablePropertiesEquals(expectedUserInfo, getPersistedUserInfo(expectedUserInfo));
    }
}
