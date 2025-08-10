package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.ConflictInterestTestSamples.*;
import static com.gosi.disclosure.domain.DisclosureTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConflictInterestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConflictInterest.class);
        ConflictInterest conflictInterest1 = getConflictInterestSample1();
        ConflictInterest conflictInterest2 = new ConflictInterest();
        assertThat(conflictInterest1).isNotEqualTo(conflictInterest2);

        conflictInterest2.setId(conflictInterest1.getId());
        assertThat(conflictInterest1).isEqualTo(conflictInterest2);

        conflictInterest2 = getConflictInterestSample2();
        assertThat(conflictInterest1).isNotEqualTo(conflictInterest2);
    }

    @Test
    void disclosureTest() {
        ConflictInterest conflictInterest = getConflictInterestRandomSampleGenerator();
        Disclosure disclosureBack = getDisclosureRandomSampleGenerator();

        conflictInterest.setDisclosure(disclosureBack);
        assertThat(conflictInterest.getDisclosure()).isEqualTo(disclosureBack);
        assertThat(disclosureBack.getConflictInterest()).isEqualTo(conflictInterest);

        conflictInterest.disclosure(null);
        assertThat(conflictInterest.getDisclosure()).isNull();
        assertThat(disclosureBack.getConflictInterest()).isNull();
    }
}
