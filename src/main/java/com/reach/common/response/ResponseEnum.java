package com.reach.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @desc Response enum
 */
public enum ResponseEnum {

    /********************************************** Define Message ****************************************************/

    SUCCESS(200, "success"),
    ERROR(101, "error"),
    CUSTOM(105, null),



    /**   USER    **/
    @ApiModelProperty("The public key has not registered")
    USER_NOT_REGISTER(1000,"The public key has not registered"),
    @ApiModelProperty("The public key has already been registered")
    USER_HAS_BEEN_REGISTERED(1001,"The public key has already been registered"),
    @ApiModelProperty("The token was expired")
    TOKEN_EXPIRED(401,"The token was expired"),
    @ApiModelProperty("The token is wrong")
    TOKEN_ERROR(402,"The token is wrong")
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
