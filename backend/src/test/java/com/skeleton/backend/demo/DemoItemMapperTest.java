package com.skeleton.backend.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DemoItemMapperTest {

    @Autowired
    private DemoItemMapper mapper;

    @Test
    void seedDataIsLoaded() {
        assertThat(mapper.selectCount(null)).isEqualTo(3);
    }

    @Test
    void insertPersistsAndAssignsId() {
        DemoItem item = new DemoItem();
        item.setName("新条目");
        item.setDescription("由集成测试插入");

        int rows = mapper.insert(item);

        assertThat(rows).isEqualTo(1);
        assertThat(item.getId()).isNotNull();
        assertThat(mapper.selectById(item.getId()).getName()).isEqualTo("新条目");
    }
}
