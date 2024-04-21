package com.aton.datastorage.repository.inmemory;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.CacheRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryCacheRepository implements CacheRepository {

    private final Map<Integer, Record> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Record save(Record record) {
        if (record.isNew()) {
            record.setId(counter.incrementAndGet());
            repository.put(record.getId(), record);
            return record;
        }
        // update, if record already exist
        return repository.computeIfPresent(record.getId(), (id, oldRecord) -> record);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Record get(int id) {
        return repository.get(id);
    }

    @Override
    public Record getByField(String fieldName, Object value) {
        return getCollection().stream()
                .filter(r -> {
                    try {
                        Field field = r.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Object fieldValue = field.get(r);
                        return value.equals(fieldValue);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }

    Collection<Record> getCollection() {
        return repository.values();
    }
}
