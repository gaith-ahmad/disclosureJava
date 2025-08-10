package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Employee getEmployeeSample1() {
        return new Employee()
            .id(1L)
            .displayName("displayName1")
            .email("email1")
            .title("title1")
            .telephoneNumber("telephoneNumber1")
            .localPhone("localPhone1")
            .department("department1")
            .physicalDeliveryOfficeName("physicalDeliveryOfficeName1")
            .distinguishedName("distinguishedName1")
            .username("username1");
    }

    public static Employee getEmployeeSample2() {
        return new Employee()
            .id(2L)
            .displayName("displayName2")
            .email("email2")
            .title("title2")
            .telephoneNumber("telephoneNumber2")
            .localPhone("localPhone2")
            .department("department2")
            .physicalDeliveryOfficeName("physicalDeliveryOfficeName2")
            .distinguishedName("distinguishedName2")
            .username("username2");
    }

    public static Employee getEmployeeRandomSampleGenerator() {
        return new Employee()
            .id(longCount.incrementAndGet())
            .displayName(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .telephoneNumber(UUID.randomUUID().toString())
            .localPhone(UUID.randomUUID().toString())
            .department(UUID.randomUUID().toString())
            .physicalDeliveryOfficeName(UUID.randomUUID().toString())
            .distinguishedName(UUID.randomUUID().toString())
            .username(UUID.randomUUID().toString());
    }
}
