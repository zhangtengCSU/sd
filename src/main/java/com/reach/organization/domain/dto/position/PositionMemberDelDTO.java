package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Date 2022/9/1 14:43
 * @Author Rookie
 */
@Data
public class PositionMemberDelDTO {
    @ApiModelProperty(value = "organizationId",required = true)
    private String organizationId;
    @ApiModelProperty(value = "positionId",required = true)
    private String positionId;
    @ApiModelProperty(value = "userId",required = true)
    private String userId;
}
