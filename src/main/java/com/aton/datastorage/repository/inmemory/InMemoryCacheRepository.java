package com.aton.datastorage.repository.inmemory;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.CacheRepository;
import com.aton.datastorage.repository.searchkey.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.aton.datastorage.repository.searchkey.DoubleSK.toDoubleSK;
import static com.aton.datastorage.repository.searchkey.IntegerSK.toIntegerSK;
import static com.aton.datastorage.repository.searchkey.LongSK.toLongSK;
import static com.aton.datastorage.repository.searchkey.StringSK.toStringSK;


@Repository
public class InMemoryCacheRepository implements CacheRepository {

    private final Map<IntegerSK, Record> idRepository = new ConcurrentHashMap<>();
    private final Map<LongSK, Record> accountRepository = new ConcurrentHashMap<>();
    private final Map<StringSK, Record> nameRepository = new ConcurrentHashMap<>();
    private final Map<DoubleSK, Record> valueRepository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Record save(Record record) {
        if (record.isNew()) {
            record.setId(counter.incrementAndGet());
            return saveInRepositories(record);
        }
        return updateInRepositories(record);
    }

    @Override
    public boolean delete(IntegerSK id) {
        return idRepository.remove(id) != null;
    }

    @Override
    public void clear() {
        idRepository.clear();
        nameRepository.clear();
        accountRepository.clear();
        valueRepository.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K extends SearchKey, T> ConcurrentHashMap<K, T> getRepository(RepositoryType type) {
        return switch (type) {
            case ID -> (ConcurrentHashMap<K, T>) idRepository;
            case ACCOUNT -> (ConcurrentHashMap<K, T>) accountRepository;
            case VALUE -> (ConcurrentHashMap<K, T>) valueRepository;
            case NAME -> (ConcurrentHashMap<K, T>) nameRepository;
        };
    }

    Collection<Record> getCollection() {
        return idRepository.values();
    }

    private Record saveInRepositories(Record record) {
        idRepository.put(toIntegerSK(record.getId()), record);
        accountRepository.put(toLongSK(record.getAccount()), record);
        nameRepository.put(toStringSK(record.getName()), record);
        valueRepository.put(toDoubleSK(record.getValue()), record);
        return record;
    }

    private Record updateInRepositories(Record record) {
        idRepository.computeIfPresent(toIntegerSK(record.getId()), (id, oldRecord) -> record);
        nameRepository.computeIfPresent(toStringSK(record.getName()), (name, oldRecord) -> record);
        accountRepository.computeIfPresent(toLongSK(record.getAccount()), (account, oldRecord) -> record);
        valueRepository.computeIfPresent(toDoubleSK(record.getValue()), (value, oldRecord) -> record);
        return record;
    }

}
