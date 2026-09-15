package com.skeleton.backend.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void successResponseHasZeroCodeAndData() {
        ApiResponse<String> response = ApiResponse.ok("payload");

        assertThat(response.getCode()).isZero();
        assertThat(response.getMessage()).isNotBlank();
        assertThat(response.getData()).isEqualTo("payload");
    }

    @Test
    void businessErrorResponseHasNonZeroCodeAndNullData() {
        ApiResponse<Void> response = ApiResponse.error(4001, "资源不存在");

        assertThat(response.getCode()).isNotZero();
        assertThat(response.getMessage()).isEqualTo("资源不存在");
        assertThat(response.getData()).isNull();
    }
}
