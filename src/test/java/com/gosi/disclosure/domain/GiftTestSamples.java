package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GiftTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Gift getGiftSample1() {
        return new Gift()
            .id(1L)
            .giftNameOrganizationGiven("giftNameOrganizationGiven1")
            .giftReason("giftReason1")
            .giftOfficialOccasion("giftOfficialOccasion1")
            .giftSpoilsQuickly("giftSpoilsQuickly1")
            .giftForPersonalUse("giftForPersonalUse1")
            .giftType("giftType1")
            .giftEstimatedValue("giftEstimatedValue1")
            .giftDateReceiving("giftDateReceiving1")
            .giftOwnDesire("giftOwnDesire1")
            .giftImpact("giftImpact1")
            .giftReasonAcceptanceRejection("giftReasonAcceptanceRejection1")
            .amountCashOffered("amountCashOffered1")
            .otherNotes("otherNotes1")
            .formalOccasionVisit("formalOccasionVisit1");
    }

    public static Gift getGiftSample2() {
        return new Gift()
            .id(2L)
            .giftNameOrganizationGiven("giftNameOrganizationGiven2")
            .giftReason("giftReason2")
            .giftOfficialOccasion("giftOfficialOccasion2")
            .giftSpoilsQuickly("giftSpoilsQuickly2")
            .giftForPersonalUse("giftForPersonalUse2")
            .giftType("giftType2")
            .giftEstimatedValue("giftEstimatedValue2")
            .giftDateReceiving("giftDateReceiving2")
            .giftOwnDesire("giftOwnDesire2")
            .giftImpact("giftImpact2")
            .giftReasonAcceptanceRejection("giftReasonAcceptanceRejection2")
            .amountCashOffered("amountCashOffered2")
            .otherNotes("otherNotes2")
            .formalOccasionVisit("formalOccasionVisit2");
    }

    public static Gift getGiftRandomSampleGenerator() {
        return new Gift()
            .id(longCount.incrementAndGet())
            .giftNameOrganizationGiven(UUID.randomUUID().toString())
            .giftReason(UUID.randomUUID().toString())
            .giftOfficialOccasion(UUID.randomUUID().toString())
            .giftSpoilsQuickly(UUID.randomUUID().toString())
            .giftForPersonalUse(UUID.randomUUID().toString())
            .giftType(UUID.randomUUID().toString())
            .giftEstimatedValue(UUID.randomUUID().toString())
            .giftDateReceiving(UUID.randomUUID().toString())
            .giftOwnDesire(UUID.randomUUID().toString())
            .giftImpact(UUID.randomUUID().toString())
            .giftReasonAcceptanceRejection(UUID.randomUUID().toString())
            .amountCashOffered(UUID.randomUUID().toString())
            .otherNotes(UUID.randomUUID().toString())
            .formalOccasionVisit(UUID.randomUUID().toString());
    }
}
