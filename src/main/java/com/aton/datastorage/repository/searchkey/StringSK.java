package com.aton.datastorage.repository.searchkey;


import lombok.Getter;

import java.util.Objects;

@Getter
public final class StringSK implements SearchKey {
    private final String value;

    public StringSK(String value) {
        this.value = value;
    }

    public static StringSK toStringSK(String sk) {
        return new StringSK(sk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringSK ssk = (StringSK) o;
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
