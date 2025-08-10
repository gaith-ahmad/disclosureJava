package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.ManagerInfoTestSamples.*;
import static com.gosi.disclosure.domain.UserInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserInfo.class);
        UserInfo userInfo1 = getUserInfoSample1();
        UserInfo userInfo2 = new UserInfo();
        assertThat(userInfo1).isNotEqualTo(userInfo2);

        userInfo2.setId(userInfo1.getId());
        assertThat(userInfo1).isEqualTo(userInfo2);

        userInfo2 = getUserInfoSample2();
        assertThat(userInfo1).isNotEqualTo(userInfo2);
    }

    @Test
    void managerinfoTest() {
        UserInfo userInfo = getUserInfoRandomSampleGenerator();
        ManagerInfo managerInfoBack = getManagerInfoRandomSampleGenerator();

        userInfo.setManagerinfo(managerInfoBack);
        assertThat(userInfo.getManagerinfo()).isEqualTo(managerInfoBack);

        userInfo.managerinfo(null);
        assertThat(userInfo.getManagerinfo()).isNull();
    }
}
