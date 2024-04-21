package com.aton.datastorage.service;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.CacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.aton.datastorage.util.ValidationUtil.checkNotFoundWithId;

@AllArgsConstructor
@Service
public class CachingService {

    private final CacheRepository cacheRepository;

    public Record get(int id) {
        return checkNotFoundWithId(cacheRepository.get(id), id);
    }

    public Record getByAccount(int id, Long account) {
        return checkNotFoundWithId(cacheRepository.getByField("account", account), id);
    }

    public Record getByName(int id, String name) {
        return checkNotFoundWithId(cacheRepository.getByField("name", name), id);
    }

    public Record getByValue(int id, Double value) {
        return checkNotFoundWithId(cacheRepository.getByField("value", value), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(cacheRepository.delete(id), id);
    }

    public void update(Record record) {
        checkNotFoundWithId(cacheRepository.save(record), record.getId());
    }

    public Record create(Record record) {
        return cacheRepository.save(record);
    }

}
