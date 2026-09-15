package com.skeleton.backend.demo;

import com.skeleton.backend.common.ApiResponse;
import com.skeleton.backend.common.PageResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo-items")
public class DemoItemController {

    private final DemoItemService service;

    public DemoItemController(DemoItemService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<PageResult<DemoItem>> list(
            @RequestParam(defaultValue = "1") long page,
            @RequestParam(defaultValue = "10") long size) {
        return ApiResponse.ok(service.page(page, size));
    }
}
