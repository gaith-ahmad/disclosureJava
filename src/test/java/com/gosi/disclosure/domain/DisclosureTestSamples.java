package com.gosi.disclosure.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DisclosureTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Disclosure getDisclosureSample1() {
        return new Disclosure()
            .id(1L)
            .purposeOfDisclosure("purposeOfDisclosure1")
            .creationDate("creationDate1")
            .name("name1")
            .jobTitle("jobTitle1")
            .jobNumber("jobNumber1")
            .ext("ext1")
            .publicAdministration("publicAdministration1")
            .administration("administration1")
            .confirm("confirm1")
            .email("email1")
            .emailDirectManager("emailDirectManager1")
            .nameDirectManager("nameDirectManager1")
            .jobNumberDirectManager("jobNumberDirectManager1")
            .jobTitleDirectManager("jobTitleDirectManager1")
            .extDirectManager("extDirectManager1")
            .file("file1")
            .filename("filename1");
    }

    public static Disclosure getDisclosureSample2() {
        return new Disclosure()
            .id(2L)
            .purposeOfDisclosure("purposeOfDisclosure2")
            .creationDate("creationDate2")
            .name("name2")
            .jobTitle("jobTitle2")
            .jobNumber("jobNumber2")
            .ext("ext2")
            .publicAdministration("publicAdministration2")
            .administration("administration2")
            .confirm("confirm2")
            .email("email2")
            .emailDirectManager("emailDirectManager2")
            .nameDirectManager("nameDirectManager2")
            .jobNumberDirectManager("jobNumberDirectManager2")
            .jobTitleDirectManager("jobTitleDirectManager2")
            .extDirectManager("extDirectManager2")
            .file("file2")
            .filename("filename2");
    }

    public static Disclosure getDisclosureRandomSampleGenerator() {
        return new Disclosure()
            .id(longCount.incrementAndGet())
            .purposeOfDisclosure(UUID.randomUUID().toString())
            .creationDate(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .jobTitle(UUID.randomUUID().toString())
            .jobNumber(UUID.randomUUID().toString())
            .ext(UUID.randomUUID().toString())
            .publicAdministration(UUID.randomUUID().toString())
            .administration(UUID.randomUUID().toString())
            .confirm(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .emailDirectManager(UUID.randomUUID().toString())
            .nameDirectManager(UUID.randomUUID().toString())
            .jobNumberDirectManager(UUID.randomUUID().toString())
            .jobTitleDirectManager(UUID.randomUUID().toString())
            .extDirectManager(UUID.randomUUID().toString())
            .file(UUID.randomUUID().toString())
            .filename(UUID.randomUUID().toString());
    }
}
