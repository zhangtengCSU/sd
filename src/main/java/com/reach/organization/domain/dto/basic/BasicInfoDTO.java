package com.reach.organization.domain.dto.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasicInfoDTO {
    @ApiModelProperty(value = "set value when update or you can get exact id",required = false)
    private String organizationId;
    @ApiModelProperty(value = "organizationName edit by user",required = true)
    private String organizationName;
    @ApiModelProperty(value = "organization description edit by user",required = true)
    private String organizationProfile;
    @ApiModelProperty(value = "after uploading logo,then get this url",required = true)
    private String logoUrl;
}
