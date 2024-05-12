package com.aton.datastorage.repository.inmemory;

import static com.aton.datastorage.repository.searchkey.DoubleSK.toDoubleSK;
import static com.aton.datastorage.repository.searchkey.IntegerSK.toIntegerSK;
import static com.aton.datastorage.repository.searchkey.LongSK.toLongSK;
import static com.aton.datastorage.repository.searchkey.StringSK.toStringSK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aton.datastorage.model.Record;
import com.aton.datastorage.repository.CacheRepository;
import com.aton.datastorage.repository.searchkey.RepositoryType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InMemoryCacheRepository.class})
@ExtendWith(SpringExtension.class)
class InMemoryCacheRepositoryTest {
    @Autowired
    private InMemoryCacheRepository inMemoryCacheRepository;
    private final Record record = new Record(2345678L, "Иванов Иван Иванович", 2035.34);

    @BeforeEach
    void setup() {
        inMemoryCacheRepository.clear();
    }

    @Test
    void testSave() {
        // Arrange
        Record resultRecord = mock(Record.class);
        doNothing().when(resultRecord).setId(Mockito.<Integer>any());
        when(resultRecord.isNew()).thenReturn(true);
        when(resultRecord.getId()).thenReturn(1);

        // Act
        Record actualSaveResult = inMemoryCacheRepository.save(resultRecord);

        // Assert
        verify(resultRecord).getId();
        verify(resultRecord).isNew();
        verify(resultRecord).setId(Mockito.<Integer>any());
        assertEquals(1, inMemoryCacheRepository.getCollection().size());
        assertSame(resultRecord, actualSaveResult);
    }


    /**
     * Method under test: {@link CacheRepository#delete(com.aton.datastorage.repository.searchkey.IntegerSK)}
     */
    @Test
    void testDelete() {
        inMemoryCacheRepository.save(record);
        // Arrange, Act and Assert
        assertTrue(inMemoryCacheRepository.delete(toIntegerSK(record.getId())));
        assertTrue(inMemoryCacheRepository.getCollection().isEmpty());
    }

    @Test
    void testGet() {
        inMemoryCacheRepository.save(record);
        Record result = inMemoryCacheRepository.getByField(RepositoryType.ID, toIntegerSK(record.getId()));
        assertEquals(record, result);
    }

    @Test
    void testGetByField1() {
        inMemoryCacheRepository.save(record);
        Record result = inMemoryCacheRepository.getByField(RepositoryType.ACCOUNT, toLongSK(record.getAccount()));
        assertEquals(record, result);
        // Arrange, Act and Assert
    }

    @Test
    void testGetByField2() {
        inMemoryCacheRepository.save(record);
        Record result = inMemoryCacheRepository.getByField(RepositoryType.NAME, toStringSK(record.getName()));
        // Arrange, Act and Assert
        assertEquals(record, result);
    }

    @Test
    void testGetByField3() {
        inMemoryCacheRepository.save(record);
        Record result = inMemoryCacheRepository.getByField(RepositoryType.VALUE, toDoubleSK(record.getValue()));
        // Arrange, Act and Assert
        assertEquals(record, result);
    }

}
