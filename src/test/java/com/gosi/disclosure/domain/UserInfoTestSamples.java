package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UserInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static UserInfo getUserInfoSample1() {
        return new UserInfo()
            .id(1L)
            .loginName("loginName1")
            .fullName("fullName1")
            .email("email1")
            .key(1L)
            .name("name1")
            .jobTitle("jobTitle1")
            .jobNumber("jobNumber1")
            .ext("ext1")
            .publicAdministration("publicAdministration1")
            .administration("administration1")
            .relativeRelationship("relativeRelationship1")
            .office("office1");
    }

    public static UserInfo getUserInfoSample2() {
        return new UserInfo()
            .id(2L)
            .loginName("loginName2")
            .fullName("fullName2")
            .email("email2")
            .key(2L)
            .name("name2")
            .jobTitle("jobTitle2")
            .jobNumber("jobNumber2")
            .ext("ext2")
            .publicAdministration("publicAdministration2")
            .administration("administration2")
            .relativeRelationship("relativeRelationship2")
            .office("office2");
    }

    public static UserInfo getUserInfoRandomSampleGenerator() {
        return new UserInfo()
            .id(longCount.incrementAndGet())
            .loginName(UUID.randomUUID().toString())
            .fullName(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .key(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .jobTitle(UUID.randomUUID().toString())
            .jobNumber(UUID.randomUUID().toString())
            .ext(UUID.randomUUID().toString())
            .publicAdministration(UUID.randomUUID().toString())
            .administration(UUID.randomUUID().toString())
            .relativeRelationship(UUID.randomUUID().toString())
            .office(UUID.randomUUID().toString());
    }
}
