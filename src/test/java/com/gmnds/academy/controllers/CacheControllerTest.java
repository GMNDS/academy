package com.gmnds.academy.controllers;

import com.gmnds.academy.infra.cache.CacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CacheControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CacheService cacheService;

    @BeforeEach
    public void setup() {
        var controller = new CacheController();
        ReflectionTestUtils.setField(controller, "cacheService", cacheService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testClearCacheEndpointCallsService() throws Exception {
        mockMvc.perform(post("/cache?cacheName=students").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cacheService).evictAllCachesValues("students");
    }
}
