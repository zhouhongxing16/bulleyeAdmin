package com.chris.bulleyeadmin.common.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

/**
 * 封装操作结果JSON数据
 *
 * @author zhao
 */
public class JsonResult {

    private boolean success;

    private String message;

    private Integer error;

    private String url;

    private Integer code;

    private HttpStatus httpStatus;

    private Object data;

    public JsonResult() {
    }

    public JsonResult failure(String msg) {
        return new JsonResult(false, null, msg,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public JsonResult(boolean success, Object data, String message,Integer code,HttpStatus httpStatus) {
        super();
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public JsonResult(Integer error, String message, String url) {
        super();
        this.error = error;
        this.message = message;
        this.url = url;
    }

    public JsonResult(boolean success, Object obj) {
        super();
        this.success = success;
        this.data = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static JsonResult getFailInstance(String msg) {
        return new JsonResult(false, msg);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
