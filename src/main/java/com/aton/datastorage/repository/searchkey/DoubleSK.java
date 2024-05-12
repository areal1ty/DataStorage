package com.aton.datastorage.repository.searchkey;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class DoubleSK implements SearchKey {
    private final Double value;

    public DoubleSK(Double value) {
        this.value = value;
    }

    public static DoubleSK toDoubleSK(Double sk) {
        return new DoubleSK(sk);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleSK ssk = (DoubleSK) o;
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
