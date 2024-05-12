package com.aton.datastorage.controller;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.CacheRepository;
import com.aton.datastorage.repository.searchkey.RepositoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.aton.datastorage.repository.searchkey.DoubleSK.toDoubleSK;
import static com.aton.datastorage.repository.searchkey.IntegerSK.toIntegerSK;
import static com.aton.datastorage.repository.searchkey.LongSK.toLongSK;
import static com.aton.datastorage.repository.searchkey.StringSK.toStringSK;
import static com.aton.datastorage.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class CachingRestController {

    private final CacheRepository cacheRepository;

    @Autowired
    public CachingRestController(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    @GetMapping("/record/{id}")
    public Record get(@PathVariable int id) {
        return checkNotFoundWithId(cacheRepository.getByField(RepositoryType.ID, toIntegerSK(id)), id);
    }

    @GetMapping("/recordByAccount/{account}")
    public Record getByAccount(int id, @PathVariable Long account) {
        return checkNotFoundWithId(cacheRepository.getByField(RepositoryType.ACCOUNT, toLongSK(account)), id);
    }

    @GetMapping("/recordByName/{name}")
    public Record getByName(int id, @PathVariable String name) {
        return checkNotFoundWithId(cacheRepository.getByField(RepositoryType.NAME, toStringSK(name)), id);
    }

    @GetMapping("/recordByValue/{value}")
    public Record getByValue(int id, @PathVariable Double value) {
        return checkNotFoundWithId(cacheRepository.getByField(RepositoryType.VALUE, toDoubleSK(value)), id);
    }

    @DeleteMapping("/record/{id}")
    public void delete(@PathVariable int id) {
        checkNotFoundWithId(cacheRepository.delete(toIntegerSK(id)), id);
    }

    @PutMapping(value = "/record")
    public void update(@Validated @RequestBody Record record) {
        checkNotFoundWithId(cacheRepository.save(record), record.getId());
    }

    @PostMapping(value = "/record")
    public Record create(@Validated @RequestBody Record record) {
        return cacheRepository.save(record);
    }

}
