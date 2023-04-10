package com.reach.common.response;

/**
 * @desc Response enum
 */
public enum ResponseEnum {

    /********************************************** Define Message ****************************************************/

    SUCCESS(200, "success"),
    ERROR(101, "error"),
    PARAM_ERROR(102, "parameter error"),
    UNAUTHORIZED(103, "unauthorized"),
    NOT_FOUND(104, "not found"),
    CUSTOM(105, null),
    WRONG_STATUS(106,"Current Operate Object in Unexpected Status "),
    UIV_RANGE_INVALID(107,"range of UIV invalid"),


    POOL_REACH_MAX(107,"obey the limitation a pool has no asset"),

    /**   USER    **/
    USER_NOT_REGISTER(1000,"The public key has not registered"),
    USER_HAS_BEEN_REGISTERED(1001,"The public key has already been registered"),
    TOKEN_EXPIRED(401,"The token was expired")
    ;


    /********************************************** Define format *****************************************************/

    private Integer code;
    private String  msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg  = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
