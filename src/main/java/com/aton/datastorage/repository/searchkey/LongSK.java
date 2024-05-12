package com.aton.datastorage.repository.searchkey;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class LongSK implements SearchKey {
    private final Long value;

    public LongSK(Long value) {
        this.value = value;
    }

    public static LongSK toLongSK(Long sk) {
        return new LongSK(sk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongSK ssk = (LongSK) o;
        return Objects.equals(value, ssk.value);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result +
                (value != null ? value.hashCode() : 0);
        return result;
    }
}

