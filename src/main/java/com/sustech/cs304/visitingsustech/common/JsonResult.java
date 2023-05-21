package com.sustech.cs304.visitingsustech.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * Set up json return format.
 *
 * @author sui_h
 */
@Data
public class JsonResult<E> {

    private int code;

    private String message;
    private E data;
    private static ObjectMapper objectMapper = new ObjectMapper();


    public JsonResult() {
        this.code = 200;
    }

    public JsonResult(E data) {
        this.code = 200;
        this.data = data;
    }

    public JsonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResult(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E> JsonResult<E> success(String message) {
        return new JsonResult<>(200, message);
    }

    public static <E> JsonResult<E> success() {
        return new JsonResult<>(200, "success");
    }

    public static <E> JsonResult<E> success(E data) {
        return new JsonResult<>(200, "success", data);
    }

    public static <E> JsonResult<E> success(String message, E data) {
        return new JsonResult<>(200, message, data);
    }

    public static <E> JsonResult<E> success(int code, String message, E data) {
        return new JsonResult<>(code, message, data);
    }

    public static <E> JsonResult<E> error(String message) {
        return new JsonResult<>(500, message);
    }

    public static <E> JsonResult<E> error() {
        return new JsonResult<>(500, "error");
    }

    public static <E> JsonResult<E> error(String message, E data) {
        return new JsonResult<>(500, message, data);
    }

    public static <E> JsonResult<E> error(int code, String message) {
        return new JsonResult<>(code, message);
    }

    public static <E> JsonResult<E> error(int code, String message, E data) {
        return new JsonResult<>(code, message, data);
    }
}