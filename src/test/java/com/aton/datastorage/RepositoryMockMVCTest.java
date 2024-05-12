package com.aton.datastorage;

import com.aton.datastorage.controller.CachingRestController;
import com.aton.datastorage.repository.CacheRepository;
import com.aton.datastorage.repository.inmemory.InMemoryCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ContextConfiguration
@WebMvcTest
public class RepositoryMockMVCTest extends AbstractResolversCommonConfig {
    public final static String CREATE_UPDATE_API = "/record";
    public final static String DELETE_GET_API = "/record/{id}";
    public final static String GET_BY_VALUE_API = "/record/{value}";
    public final static String GET_BY_ACCOUNT_API = "/record/{account}";
    public final static String GET_BY_NAME_API = "/record/{name}";

    @Autowired
    protected MockMvc mockMvc;
    protected CachingRestController cachingRestController;
    protected CacheRepository cacheRepository;

    @BeforeEach
    public void setup() {
        cacheRepository = mock(InMemoryCacheRepository.class);
        cachingRestController = new CachingRestController(cacheRepository);
    }

    @Test
    public void getRecordAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(DELETE_GET_API, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.record[*].recordId").value(1));
    }

    @Test
    public void getRecordByNameAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(DELETE_GET_API, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.record[*].recordId").value(1));
    }

    @Test
    public void getRecordByAccountAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(DELETE_GET_API, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.record[*].recordId").value(1));
    }

    @Test
    public void getRecordByValueAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(DELETE_GET_API, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.record[*].recordId").value(1));
    }
}
