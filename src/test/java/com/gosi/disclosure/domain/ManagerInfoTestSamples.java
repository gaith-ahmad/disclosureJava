package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ManagerInfoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ManagerInfo getManagerInfoSample1() {
        return new ManagerInfo()
            .id(1L)
            .emailDirectManager("emailDirectManager1")
            .nameDirectManager("nameDirectManager1")
            .jobNumberDirectManager("jobNumberDirectManager1")
            .jobTitleDirectManager("jobTitleDirectManager1")
            .extDirectManager("extDirectManager1");
    }

    public static ManagerInfo getManagerInfoSample2() {
        return new ManagerInfo()
            .id(2L)
            .emailDirectManager("emailDirectManager2")
            .nameDirectManager("nameDirectManager2")
            .jobNumberDirectManager("jobNumberDirectManager2")
            .jobTitleDirectManager("jobTitleDirectManager2")
            .extDirectManager("extDirectManager2");
    }

    public static ManagerInfo getManagerInfoRandomSampleGenerator() {
        return new ManagerInfo()
            .id(longCount.incrementAndGet())
            .emailDirectManager(UUID.randomUUID().toString())
            .nameDirectManager(UUID.randomUUID().toString())
            .jobNumberDirectManager(UUID.randomUUID().toString())
            .jobTitleDirectManager(UUID.randomUUID().toString())
            .extDirectManager(UUID.randomUUID().toString());
    }
}
