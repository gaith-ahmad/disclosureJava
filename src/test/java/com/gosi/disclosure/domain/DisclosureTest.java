package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.ConflictInterestTestSamples.*;
import static com.gosi.disclosure.domain.DisclosureTestSamples.*;
import static com.gosi.disclosure.domain.GiftTestSamples.*;
import static com.gosi.disclosure.domain.RelativesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DisclosureTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disclosure.class);
        Disclosure disclosure1 = getDisclosureSample1();
        Disclosure disclosure2 = new Disclosure();
        assertThat(disclosure1).isNotEqualTo(disclosure2);

        disclosure2.setId(disclosure1.getId());
        assertThat(disclosure1).isEqualTo(disclosure2);

        disclosure2 = getDisclosureSample2();
        assertThat(disclosure1).isNotEqualTo(disclosure2);
    }

    @Test
    void giftTest() {
        Disclosure disclosure = getDisclosureRandomSampleGenerator();
        Gift giftBack = getGiftRandomSampleGenerator();

        disclosure.setGift(giftBack);
        assertThat(disclosure.getGift()).isEqualTo(giftBack);

        disclosure.gift(null);
        assertThat(disclosure.getGift()).isNull();
    }

    @Test
    void conflictInterestTest() {
        Disclosure disclosure = getDisclosureRandomSampleGenerator();
        ConflictInterest conflictInterestBack = getConflictInterestRandomSampleGenerator();

        disclosure.setConflictInterest(conflictInterestBack);
        assertThat(disclosure.getConflictInterest()).isEqualTo(conflictInterestBack);

        disclosure.conflictInterest(null);
        assertThat(disclosure.getConflictInterest()).isNull();
    }

    @Test
    void relativesTest() {
        Disclosure disclosure = getDisclosureRandomSampleGenerator();
        Relatives relativesBack = getRelativesRandomSampleGenerator();

        disclosure.addRelatives(relativesBack);
        assertThat(disclosure.getRelatives()).containsOnly(relativesBack);
        assertThat(relativesBack.getDisclosure()).isEqualTo(disclosure);

        disclosure.removeRelatives(relativesBack);
        assertThat(disclosure.getRelatives()).doesNotContain(relativesBack);
        assertThat(relativesBack.getDisclosure()).isNull();

        disclosure.relatives(new HashSet<>(Set.of(relativesBack)));
        assertThat(disclosure.getRelatives()).containsOnly(relativesBack);
        assertThat(relativesBack.getDisclosure()).isEqualTo(disclosure);

        disclosure.setRelatives(new HashSet<>());
        assertThat(disclosure.getRelatives()).doesNotContain(relativesBack);
        assertThat(relativesBack.getDisclosure()).isNull();
    }
}
