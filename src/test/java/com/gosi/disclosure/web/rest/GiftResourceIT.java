package com.gosi.disclosure.web.rest;

import static com.gosi.disclosure.domain.GiftAsserts.*;
import static com.gosi.disclosure.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gosi.disclosure.IntegrationTest;
import com.gosi.disclosure.domain.Gift;
import com.gosi.disclosure.repository.GiftRepository;
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
 * Integration tests for the {@link GiftResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GiftResourceIT {

    private static final String DEFAULT_GIFT_NAME_ORGANIZATION_GIVEN = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_NAME_ORGANIZATION_GIVEN = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_OFFICIAL_OCCASION = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_OFFICIAL_OCCASION = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_SPOILS_QUICKLY = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_SPOILS_QUICKLY = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_FOR_PERSONAL_USE = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_FOR_PERSONAL_USE = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_ESTIMATED_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_ESTIMATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_DATE_RECEIVING = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_DATE_RECEIVING = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_OWN_DESIRE = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_OWN_DESIRE = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_IMPACT = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_IMPACT = "BBBBBBBBBB";

    private static final String DEFAULT_GIFT_REASON_ACCEPTANCE_REJECTION = "AAAAAAAAAA";
    private static final String UPDATED_GIFT_REASON_ACCEPTANCE_REJECTION = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_CASH_OFFERED = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_CASH_OFFERED = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_FORMAL_OCCASION_VISIT = "AAAAAAAAAA";
    private static final String UPDATED_FORMAL_OCCASION_VISIT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gifts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private GiftRepository giftRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGiftMockMvc;

    private Gift gift;

    private Gift insertedGift;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createEntity() {
        return new Gift()
            .giftNameOrganizationGiven(DEFAULT_GIFT_NAME_ORGANIZATION_GIVEN)
            .giftReason(DEFAULT_GIFT_REASON)
            .giftOfficialOccasion(DEFAULT_GIFT_OFFICIAL_OCCASION)
            .giftSpoilsQuickly(DEFAULT_GIFT_SPOILS_QUICKLY)
            .giftForPersonalUse(DEFAULT_GIFT_FOR_PERSONAL_USE)
            .giftType(DEFAULT_GIFT_TYPE)
            .giftEstimatedValue(DEFAULT_GIFT_ESTIMATED_VALUE)
            .giftDateReceiving(DEFAULT_GIFT_DATE_RECEIVING)
            .giftOwnDesire(DEFAULT_GIFT_OWN_DESIRE)
            .giftImpact(DEFAULT_GIFT_IMPACT)
            .giftReasonAcceptanceRejection(DEFAULT_GIFT_REASON_ACCEPTANCE_REJECTION)
            .amountCashOffered(DEFAULT_AMOUNT_CASH_OFFERED)
            .otherNotes(DEFAULT_OTHER_NOTES)
            .formalOccasionVisit(DEFAULT_FORMAL_OCCASION_VISIT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gift createUpdatedEntity() {
        return new Gift()
            .giftNameOrganizationGiven(UPDATED_GIFT_NAME_ORGANIZATION_GIVEN)
            .giftReason(UPDATED_GIFT_REASON)
            .giftOfficialOccasion(UPDATED_GIFT_OFFICIAL_OCCASION)
            .giftSpoilsQuickly(UPDATED_GIFT_SPOILS_QUICKLY)
            .giftForPersonalUse(UPDATED_GIFT_FOR_PERSONAL_USE)
            .giftType(UPDATED_GIFT_TYPE)
            .giftEstimatedValue(UPDATED_GIFT_ESTIMATED_VALUE)
            .giftDateReceiving(UPDATED_GIFT_DATE_RECEIVING)
            .giftOwnDesire(UPDATED_GIFT_OWN_DESIRE)
            .giftImpact(UPDATED_GIFT_IMPACT)
            .giftReasonAcceptanceRejection(UPDATED_GIFT_REASON_ACCEPTANCE_REJECTION)
            .amountCashOffered(UPDATED_AMOUNT_CASH_OFFERED)
            .otherNotes(UPDATED_OTHER_NOTES)
            .formalOccasionVisit(UPDATED_FORMAL_OCCASION_VISIT);
    }

    @BeforeEach
    void initTest() {
        gift = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedGift != null) {
            giftRepository.delete(insertedGift);
            insertedGift = null;
        }
    }

    @Test
    @Transactional
    void createGift() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Gift
        var returnedGift = om.readValue(
            restGiftMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Gift.class
        );

        // Validate the Gift in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertGiftUpdatableFieldsEquals(returnedGift, getPersistedGift(returnedGift));

        insertedGift = returnedGift;
    }

    @Test
    @Transactional
    void createGiftWithExistingId() throws Exception {
        // Create the Gift with an existing ID
        gift.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkGiftOfficialOccasionIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftOfficialOccasion(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftSpoilsQuicklyIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftSpoilsQuickly(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftForPersonalUseIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftForPersonalUse(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftTypeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftType(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftDateReceivingIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftDateReceiving(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftOwnDesireIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftOwnDesire(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGiftImpactIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        gift.setGiftImpact(null);

        // Create the Gift, which fails.

        restGiftMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllGifts() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        // Get all the giftList
        restGiftMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gift.getId().intValue())))
            .andExpect(jsonPath("$.[*].giftNameOrganizationGiven").value(hasItem(DEFAULT_GIFT_NAME_ORGANIZATION_GIVEN)))
            .andExpect(jsonPath("$.[*].giftReason").value(hasItem(DEFAULT_GIFT_REASON)))
            .andExpect(jsonPath("$.[*].giftOfficialOccasion").value(hasItem(DEFAULT_GIFT_OFFICIAL_OCCASION)))
            .andExpect(jsonPath("$.[*].giftSpoilsQuickly").value(hasItem(DEFAULT_GIFT_SPOILS_QUICKLY)))
            .andExpect(jsonPath("$.[*].giftForPersonalUse").value(hasItem(DEFAULT_GIFT_FOR_PERSONAL_USE)))
            .andExpect(jsonPath("$.[*].giftType").value(hasItem(DEFAULT_GIFT_TYPE)))
            .andExpect(jsonPath("$.[*].giftEstimatedValue").value(hasItem(DEFAULT_GIFT_ESTIMATED_VALUE)))
            .andExpect(jsonPath("$.[*].giftDateReceiving").value(hasItem(DEFAULT_GIFT_DATE_RECEIVING)))
            .andExpect(jsonPath("$.[*].giftOwnDesire").value(hasItem(DEFAULT_GIFT_OWN_DESIRE)))
            .andExpect(jsonPath("$.[*].giftImpact").value(hasItem(DEFAULT_GIFT_IMPACT)))
            .andExpect(jsonPath("$.[*].giftReasonAcceptanceRejection").value(hasItem(DEFAULT_GIFT_REASON_ACCEPTANCE_REJECTION)))
            .andExpect(jsonPath("$.[*].amountCashOffered").value(hasItem(DEFAULT_AMOUNT_CASH_OFFERED)))
            .andExpect(jsonPath("$.[*].otherNotes").value(hasItem(DEFAULT_OTHER_NOTES)))
            .andExpect(jsonPath("$.[*].formalOccasionVisit").value(hasItem(DEFAULT_FORMAL_OCCASION_VISIT)));
    }

    @Test
    @Transactional
    void getGift() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        // Get the gift
        restGiftMockMvc
            .perform(get(ENTITY_API_URL_ID, gift.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gift.getId().intValue()))
            .andExpect(jsonPath("$.giftNameOrganizationGiven").value(DEFAULT_GIFT_NAME_ORGANIZATION_GIVEN))
            .andExpect(jsonPath("$.giftReason").value(DEFAULT_GIFT_REASON))
            .andExpect(jsonPath("$.giftOfficialOccasion").value(DEFAULT_GIFT_OFFICIAL_OCCASION))
            .andExpect(jsonPath("$.giftSpoilsQuickly").value(DEFAULT_GIFT_SPOILS_QUICKLY))
            .andExpect(jsonPath("$.giftForPersonalUse").value(DEFAULT_GIFT_FOR_PERSONAL_USE))
            .andExpect(jsonPath("$.giftType").value(DEFAULT_GIFT_TYPE))
            .andExpect(jsonPath("$.giftEstimatedValue").value(DEFAULT_GIFT_ESTIMATED_VALUE))
            .andExpect(jsonPath("$.giftDateReceiving").value(DEFAULT_GIFT_DATE_RECEIVING))
            .andExpect(jsonPath("$.giftOwnDesire").value(DEFAULT_GIFT_OWN_DESIRE))
            .andExpect(jsonPath("$.giftImpact").value(DEFAULT_GIFT_IMPACT))
            .andExpect(jsonPath("$.giftReasonAcceptanceRejection").value(DEFAULT_GIFT_REASON_ACCEPTANCE_REJECTION))
            .andExpect(jsonPath("$.amountCashOffered").value(DEFAULT_AMOUNT_CASH_OFFERED))
            .andExpect(jsonPath("$.otherNotes").value(DEFAULT_OTHER_NOTES))
            .andExpect(jsonPath("$.formalOccasionVisit").value(DEFAULT_FORMAL_OCCASION_VISIT));
    }

    @Test
    @Transactional
    void getNonExistingGift() throws Exception {
        // Get the gift
        restGiftMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingGift() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the gift
        Gift updatedGift = giftRepository.findById(gift.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedGift are not directly saved in db
        em.detach(updatedGift);
        updatedGift
            .giftNameOrganizationGiven(UPDATED_GIFT_NAME_ORGANIZATION_GIVEN)
            .giftReason(UPDATED_GIFT_REASON)
            .giftOfficialOccasion(UPDATED_GIFT_OFFICIAL_OCCASION)
            .giftSpoilsQuickly(UPDATED_GIFT_SPOILS_QUICKLY)
            .giftForPersonalUse(UPDATED_GIFT_FOR_PERSONAL_USE)
            .giftType(UPDATED_GIFT_TYPE)
            .giftEstimatedValue(UPDATED_GIFT_ESTIMATED_VALUE)
            .giftDateReceiving(UPDATED_GIFT_DATE_RECEIVING)
            .giftOwnDesire(UPDATED_GIFT_OWN_DESIRE)
            .giftImpact(UPDATED_GIFT_IMPACT)
            .giftReasonAcceptanceRejection(UPDATED_GIFT_REASON_ACCEPTANCE_REJECTION)
            .amountCashOffered(UPDATED_AMOUNT_CASH_OFFERED)
            .otherNotes(UPDATED_OTHER_NOTES)
            .formalOccasionVisit(UPDATED_FORMAL_OCCASION_VISIT);

        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedGift.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedGiftToMatchAllProperties(updatedGift);
    }

    @Test
    @Transactional
    void putNonExistingGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(put(ENTITY_API_URL_ID, gift.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gift)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .giftNameOrganizationGiven(UPDATED_GIFT_NAME_ORGANIZATION_GIVEN)
            .giftSpoilsQuickly(UPDATED_GIFT_SPOILS_QUICKLY)
            .giftEstimatedValue(UPDATED_GIFT_ESTIMATED_VALUE)
            .giftDateReceiving(UPDATED_GIFT_DATE_RECEIVING)
            .giftOwnDesire(UPDATED_GIFT_OWN_DESIRE)
            .amountCashOffered(UPDATED_AMOUNT_CASH_OFFERED)
            .otherNotes(UPDATED_OTHER_NOTES)
            .formalOccasionVisit(UPDATED_FORMAL_OCCASION_VISIT);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGiftUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedGift, gift), getPersistedGift(gift));
    }

    @Test
    @Transactional
    void fullUpdateGiftWithPatch() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the gift using partial update
        Gift partialUpdatedGift = new Gift();
        partialUpdatedGift.setId(gift.getId());

        partialUpdatedGift
            .giftNameOrganizationGiven(UPDATED_GIFT_NAME_ORGANIZATION_GIVEN)
            .giftReason(UPDATED_GIFT_REASON)
            .giftOfficialOccasion(UPDATED_GIFT_OFFICIAL_OCCASION)
            .giftSpoilsQuickly(UPDATED_GIFT_SPOILS_QUICKLY)
            .giftForPersonalUse(UPDATED_GIFT_FOR_PERSONAL_USE)
            .giftType(UPDATED_GIFT_TYPE)
            .giftEstimatedValue(UPDATED_GIFT_ESTIMATED_VALUE)
            .giftDateReceiving(UPDATED_GIFT_DATE_RECEIVING)
            .giftOwnDesire(UPDATED_GIFT_OWN_DESIRE)
            .giftImpact(UPDATED_GIFT_IMPACT)
            .giftReasonAcceptanceRejection(UPDATED_GIFT_REASON_ACCEPTANCE_REJECTION)
            .amountCashOffered(UPDATED_AMOUNT_CASH_OFFERED)
            .otherNotes(UPDATED_OTHER_NOTES)
            .formalOccasionVisit(UPDATED_FORMAL_OCCASION_VISIT);

        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGift.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGift))
            )
            .andExpect(status().isOk());

        // Validate the Gift in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGiftUpdatableFieldsEquals(partialUpdatedGift, getPersistedGift(partialUpdatedGift));
    }

    @Test
    @Transactional
    void patchNonExistingGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(patch(ENTITY_API_URL_ID, gift.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(gift)))
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(gift))
            )
            .andExpect(status().isBadRequest());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamGift() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        gift.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGiftMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(gift)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Gift in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteGift() throws Exception {
        // Initialize the database
        insertedGift = giftRepository.saveAndFlush(gift);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the gift
        restGiftMockMvc
            .perform(delete(ENTITY_API_URL_ID, gift.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return giftRepository.count();
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

    protected Gift getPersistedGift(Gift gift) {
        return giftRepository.findById(gift.getId()).orElseThrow();
    }

    protected void assertPersistedGiftToMatchAllProperties(Gift expectedGift) {
        assertGiftAllPropertiesEquals(expectedGift, getPersistedGift(expectedGift));
    }

    protected void assertPersistedGiftToMatchUpdatableProperties(Gift expectedGift) {
        assertGiftAllUpdatablePropertiesEquals(expectedGift, getPersistedGift(expectedGift));
    }
}
