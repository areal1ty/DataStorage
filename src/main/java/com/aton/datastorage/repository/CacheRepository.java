package com.aton.datastorage.repository;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.searchkey.IntegerSK;
import com.aton.datastorage.repository.searchkey.RepositoryType;
import com.aton.datastorage.repository.searchkey.SearchKey;

import java.util.concurrent.ConcurrentHashMap;

public interface CacheRepository {
    Record save(Record record);
    boolean delete(IntegerSK id);
    void clear();
    <K extends SearchKey, T> ConcurrentHashMap<K, T> getRepository(RepositoryType type);
    default <K extends SearchKey, T> T getByField(RepositoryType type, K key) {
        ConcurrentHashMap<K, T> repo = getRepository(type);
        return repo.get(key);
    }

}
