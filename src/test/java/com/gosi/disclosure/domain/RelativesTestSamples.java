package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RelativesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Relatives getRelativesSample1() {
        return new Relatives()
            .id(1L)
            .nameDiscloser("nameDiscloser1")
            .nameRelative("nameRelative1")
            .jobTitleRelative("jobTitleRelative1")
            .relativeJobNumber("relativeJobNumber1")
            .emailRelative("emailRelative1")
            .relativeExtensionNumber("relativeExtensionNumber1")
            .relativeRelationship("relativeRelationship1")
            .generalAdministrationRelative("generalAdministrationRelative1")
            .administrationRelative("administrationRelative1");
    }

    public static Relatives getRelativesSample2() {
        return new Relatives()
            .id(2L)
            .nameDiscloser("nameDiscloser2")
            .nameRelative("nameRelative2")
            .jobTitleRelative("jobTitleRelative2")
            .relativeJobNumber("relativeJobNumber2")
            .emailRelative("emailRelative2")
            .relativeExtensionNumber("relativeExtensionNumber2")
            .relativeRelationship("relativeRelationship2")
            .generalAdministrationRelative("generalAdministrationRelative2")
            .administrationRelative("administrationRelative2");
    }

    public static Relatives getRelativesRandomSampleGenerator() {
        return new Relatives()
            .id(longCount.incrementAndGet())
            .nameDiscloser(UUID.randomUUID().toString())
            .nameRelative(UUID.randomUUID().toString())
            .jobTitleRelative(UUID.randomUUID().toString())
            .relativeJobNumber(UUID.randomUUID().toString())
            .emailRelative(UUID.randomUUID().toString())
            .relativeExtensionNumber(UUID.randomUUID().toString())
            .relativeRelationship(UUID.randomUUID().toString())
            .generalAdministrationRelative(UUID.randomUUID().toString())
            .administrationRelative(UUID.randomUUID().toString());
    }
}
