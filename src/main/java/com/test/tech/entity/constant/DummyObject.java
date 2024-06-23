package com.test.tech.entity.constant;

import com.test.tech.entity.dao.UserAccount;

public class DummyObject {
    private DummyObject(){}

    public static UserAccount dummyUser(){
        return UserAccount.builder()
                .userId(1L)
                .userName("dummy")
                .fullName("DUMMY")
                .build();
    }
}
