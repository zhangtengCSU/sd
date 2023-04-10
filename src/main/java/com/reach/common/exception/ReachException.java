package com.reach.common.exception;

import com.reach.common.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * exception object
 */
@Slf4j
public class ReachException extends  RuntimeException{
    private Integer code;
    private String  msg;

    private ReachException(){};

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ReachException build(Integer code, String msg) {
        ReachException reachException = new ReachException();
        reachException.setCode(code);
        reachException.setMsg(msg);
        // error log
        if (ResponseEnum.ERROR.getCode().equals(code)) {
            log.error(msg);
        }
        return reachException;
    }

    public static ReachException build(ResponseEnum responseEnum) {
        return build(responseEnum.getCode(), responseEnum.getMsg());
    }

    public static ReachException build(ResponseEnum responseEnum, String message) {
        return build(responseEnum.getCode(), message);
    }
}
