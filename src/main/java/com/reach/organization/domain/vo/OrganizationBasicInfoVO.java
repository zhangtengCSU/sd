package com.reach.organization.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2022/8/30 10:39
 * @Author Rookie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationBasicInfoVO {
    @ApiModelProperty(value = "organizationName edit by user")
    private String organizationName;
    @ApiModelProperty(value = "organization description edit by user")
    private String organizationProfile;
    @ApiModelProperty(value = "after uploading logo,then get this url")
    private String logoUrl;
}
