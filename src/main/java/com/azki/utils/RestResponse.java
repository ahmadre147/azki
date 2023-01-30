package com.azki.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RestResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Object data;
    private List<Object> errors = new ArrayList<Object>();
    private Boolean status = true;
    private String message;
    private Integer code = 200;

    public RestResponse() {

    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public RestResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestResponse(Object data, Boolean status, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestResponse(Boolean status, Integer code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public RestResponse(Boolean status, Integer code, String message, List errors) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public RestResponse(Boolean status, List errors) {
        this.status = status;
        this.errors = errors;
    }


    public RestResponse(Boolean status, String message, List errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getErrors() {
        return errors;
    }

    public void setErrors(List errors) {
        this.errors = errors;
    }

    public void addError(Object error) {
        errors.add(error);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}