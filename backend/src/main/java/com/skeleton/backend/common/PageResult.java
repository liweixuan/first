package com.skeleton.backend.common;

import java.util.List;

/**
 * 分页结果契约：records（当页记录）、total（总记录数）、current（当前页）、size（页大小）。
 */
public record PageResult<T>(List<T> records, long total, long current, long size) {
}
