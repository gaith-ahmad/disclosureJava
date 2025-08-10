package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.DisclosureTestSamples.*;
import static com.gosi.disclosure.domain.GiftTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GiftTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gift.class);
        Gift gift1 = getGiftSample1();
        Gift gift2 = new Gift();
        assertThat(gift1).isNotEqualTo(gift2);

        gift2.setId(gift1.getId());
        assertThat(gift1).isEqualTo(gift2);

        gift2 = getGiftSample2();
        assertThat(gift1).isNotEqualTo(gift2);
    }

    @Test
    void disclosureTest() {
        Gift gift = getGiftRandomSampleGenerator();
        Disclosure disclosureBack = getDisclosureRandomSampleGenerator();

        gift.setDisclosure(disclosureBack);
        assertThat(gift.getDisclosure()).isEqualTo(disclosureBack);
        assertThat(disclosureBack.getGift()).isEqualTo(gift);

        gift.disclosure(null);
        assertThat(gift.getDisclosure()).isNull();
        assertThat(disclosureBack.getGift()).isNull();
    }
}
