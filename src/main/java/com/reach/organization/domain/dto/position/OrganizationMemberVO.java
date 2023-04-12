package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2022/8/29 11:04
 * @Author Rookie
 */
@Data
@Builder
public class OrganizationMemberVO {
    @ApiModelProperty("the userId")
    private String userId;
    @ApiModelProperty("the userName")
    private String userName;
    @ApiModelProperty("the user logo url")
    private String logo;
    @ApiModelProperty("the positionId")
    private String positionId;
}
