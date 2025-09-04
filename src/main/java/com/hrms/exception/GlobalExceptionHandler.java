package com.hrms.exception;

import com.hrms.domain.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        log.error("Error: ", e);

        // 自定义消息和状态码
        String errorMessage = StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败";
        Result<String> result = Result.error(errorMessage);

        // 返回 ResponseEntity，包含响应体和状态码
        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR); // 500 - Internal Server Error
    }
}
