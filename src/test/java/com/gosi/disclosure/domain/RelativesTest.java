package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.DisclosureTestSamples.*;
import static com.gosi.disclosure.domain.RelativesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RelativesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Relatives.class);
        Relatives relatives1 = getRelativesSample1();
        Relatives relatives2 = new Relatives();
        assertThat(relatives1).isNotEqualTo(relatives2);

        relatives2.setId(relatives1.getId());
        assertThat(relatives1).isEqualTo(relatives2);

        relatives2 = getRelativesSample2();
        assertThat(relatives1).isNotEqualTo(relatives2);
    }

    @Test
    void disclosureTest() {
        Relatives relatives = getRelativesRandomSampleGenerator();
        Disclosure disclosureBack = getDisclosureRandomSampleGenerator();

        relatives.setDisclosure(disclosureBack);
        assertThat(relatives.getDisclosure()).isEqualTo(disclosureBack);

        relatives.disclosure(null);
        assertThat(relatives.getDisclosure()).isNull();
    }
}
