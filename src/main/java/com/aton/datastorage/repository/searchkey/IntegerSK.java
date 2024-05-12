package com.aton.datastorage.repository.searchkey;

import lombok.Data;

import java.util.Objects;

@Data
public final class IntegerSK implements SearchKey {
    private final Integer value;

    public IntegerSK(Integer value) {
        this.value = value;
    }

    public static IntegerSK toIntegerSK(Integer value) {
        return new IntegerSK(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerSK ssk = (IntegerSK) o;
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



