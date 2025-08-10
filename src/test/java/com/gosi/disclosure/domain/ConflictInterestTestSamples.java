package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ConflictInterestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ConflictInterest getConflictInterestSample1() {
        return new ConflictInterest()
            .id(1L)
            .conflictInterestClassification("conflictInterestClassification1")
            .conflictInterestEntityIndividual("conflictInterestEntityIndividual1")
            .conflictInterestDateArose("conflictInterestDateArose1")
            .conflictInterestImpact("conflictInterestImpact1")
            .relationshipEntityIndividual("relationshipEntityIndividual1")
            .affectPermission("affectPermission1")
            .caseDetails("caseDetails1")
            .discloseInspector("discloseInspector1")
            .facilityRegisteredInsuranceName("facilityRegisteredInsuranceName1")
            .office("office1");
    }

    public static ConflictInterest getConflictInterestSample2() {
        return new ConflictInterest()
            .id(2L)
            .conflictInterestClassification("conflictInterestClassification2")
            .conflictInterestEntityIndividual("conflictInterestEntityIndividual2")
            .conflictInterestDateArose("conflictInterestDateArose2")
            .conflictInterestImpact("conflictInterestImpact2")
            .relationshipEntityIndividual("relationshipEntityIndividual2")
            .affectPermission("affectPermission2")
            .caseDetails("caseDetails2")
            .discloseInspector("discloseInspector2")
            .facilityRegisteredInsuranceName("facilityRegisteredInsuranceName2")
            .office("office2");
    }

    public static ConflictInterest getConflictInterestRandomSampleGenerator() {
        return new ConflictInterest()
            .id(longCount.incrementAndGet())
            .conflictInterestClassification(UUID.randomUUID().toString())
            .conflictInterestEntityIndividual(UUID.randomUUID().toString())
            .conflictInterestDateArose(UUID.randomUUID().toString())
            .conflictInterestImpact(UUID.randomUUID().toString())
            .relationshipEntityIndividual(UUID.randomUUID().toString())
            .affectPermission(UUID.randomUUID().toString())
            .caseDetails(UUID.randomUUID().toString())
            .discloseInspector(UUID.randomUUID().toString())
            .facilityRegisteredInsuranceName(UUID.randomUUID().toString())
            .office(UUID.randomUUID().toString());
    }
}
