package com.aton.datastorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
public class Record {

    private Integer id;
    private Long account;
    private String name;
    private Double value;

    public Record(Long account, String name, Double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
