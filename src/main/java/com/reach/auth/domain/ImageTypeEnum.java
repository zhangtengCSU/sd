package com.reach.auth.domain;


import lombok.Getter;

@Getter
public enum ImageTypeEnum {

    ORGANIZATION(0,"organizationLogo"),
    USER(1,"userLogo"),
    ;


    private Integer code;
    private String desc;

    ImageTypeEnum(Integer code,String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getDesc(Integer code){
        for (ImageTypeEnum p : ImageTypeEnum.values()) {
            if (p.getCode().equals(code)) {
                return p.getDesc();
            }
        }
        return null;
    }


}