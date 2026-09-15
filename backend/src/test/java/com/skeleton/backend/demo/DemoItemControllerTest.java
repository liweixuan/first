package com.skeleton.backend.demo;

import org.junit.jupiter.api.BeforeEach;
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
class DemoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DemoItemMapper mapper;

    @BeforeEach
    void resetSeedData() {
        mapper.delete(null);
        insertSeed("条目一", "种子数据 1");
        insertSeed("条目二", "种子数据 2");
        insertSeed("条目三", "种子数据 3");
    }

    private void insertSeed(String name, String description) {
        DemoItem item = new DemoItem();
        item.setName(name);
        item.setDescription(description);
        mapper.insert(item);
    }

    @Test
    void paginatedQueryReturnsPageFields() throws Exception {
        mockMvc.perform(get("/api/demo-items").param("page", "1").param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.records.length()").value(2))
                .andExpect(jsonPath("$.data.total").value(3))
                .andExpect(jsonPath("$.data.current").value(1))
                .andExpect(jsonPath("$.data.size").value(2));
    }

    @Test
    void emptyTableReturnsZeroTotalAndEmptyRecords() throws Exception {
        mapper.delete(null);

        mockMvc.perform(get("/api/demo-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.total").value(0))
                .andExpect(jsonPath("$.data.records.length()").value(0));
    }
}
