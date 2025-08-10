package com.gosi.disclosure.domain;

import static com.gosi.disclosure.domain.ManagerInfoTestSamples.*;
import static com.gosi.disclosure.domain.UserInfoTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gosi.disclosure.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManagerInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManagerInfo.class);
        ManagerInfo managerInfo1 = getManagerInfoSample1();
        ManagerInfo managerInfo2 = new ManagerInfo();
        assertThat(managerInfo1).isNotEqualTo(managerInfo2);

        managerInfo2.setId(managerInfo1.getId());
        assertThat(managerInfo1).isEqualTo(managerInfo2);

        managerInfo2 = getManagerInfoSample2();
        assertThat(managerInfo1).isNotEqualTo(managerInfo2);
    }

    @Test
    void userInfoTest() {
        ManagerInfo managerInfo = getManagerInfoRandomSampleGenerator();
        UserInfo userInfoBack = getUserInfoRandomSampleGenerator();

        managerInfo.setUserInfo(userInfoBack);
        assertThat(managerInfo.getUserInfo()).isEqualTo(userInfoBack);
        assertThat(userInfoBack.getManagerinfo()).isEqualTo(managerInfo);

        managerInfo.userInfo(null);
        assertThat(managerInfo.getUserInfo()).isNull();
        assertThat(userInfoBack.getManagerinfo()).isNull();
    }
}
