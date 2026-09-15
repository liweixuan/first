package com.skeleton.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiDocTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void docPageIsAccessible() throws Exception {
        mockMvc.perform(get("/doc.html"))
                .andExpect(status().isOk());
    }

    @Test
    void apiDocsListSkeletonEndpoints() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.title").value("Backend Skeleton API"))
                .andExpect(jsonPath("$.paths['/api/health']").exists())
                .andExpect(jsonPath("$.paths['/api/demo-items']").exists());
    }
}
