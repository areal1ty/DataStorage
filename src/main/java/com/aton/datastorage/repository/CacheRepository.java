package com.aton.datastorage.repository;

import com.aton.datastorage.model.Record;

public interface CacheRepository {
    Record save(Record record);
    boolean delete(int id);
    Record get(int id);
    Record getByField(String fieldName, Object o);

}
