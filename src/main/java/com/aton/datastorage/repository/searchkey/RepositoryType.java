package com.aton.datastorage.repository.searchkey;

import lombok.Getter;

@Getter
public enum RepositoryType {
    ID(IntegerSK.class),
    ACCOUNT(LongSK.class),
    VALUE(DoubleSK.class),
    NAME(StringSK.class);

    private final Class<? extends SearchKey> keyType;

    RepositoryType(Class<? extends SearchKey> keyType) {
        this.keyType = keyType;
    }

    }
