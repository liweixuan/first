package com.skeleton.backend.common;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void businessExceptionReturnsEnvelopeWithItsCodeAndHttp200() {
        BizException exception = new BizException(4001, "资源不存在");

        ResponseEntity<ApiResponse<Void>> response = handler.handleBizException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(4001);
        assertThat(response.getBody().getMessage()).isEqualTo("资源不存在");
        assertThat(response.getBody().getData()).isNull();
    }

    @Test
    void unknownExceptionReturnsGenericErrorWithoutStackDetails() {
        RuntimeException exception = new RuntimeException("secret internal detail: boom");

        ResponseEntity<ApiResponse<Void>> response = handler.handleUnknownException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(5000);
        assertThat(response.getBody().getMessage()).doesNotContain("secret internal detail");
        assertThat(response.getBody().getMessage()).doesNotContain("RuntimeException");
    }
}
