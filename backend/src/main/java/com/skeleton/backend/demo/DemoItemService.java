package com.skeleton.backend.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skeleton.backend.common.PageResult;
import org.springframework.stereotype.Service;

@Service
public class DemoItemService {

    private final DemoItemMapper mapper;

    public DemoItemService(DemoItemMapper mapper) {
        this.mapper = mapper;
    }

    public PageResult<DemoItem> page(long current, long size) {
        Page<DemoItem> page = mapper.selectPage(new Page<>(current, size), null);
        return new PageResult<>(page.getRecords(), page.getTotal(), page.getCurrent(), page.getSize());
    }
}
