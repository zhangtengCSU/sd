package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Description
 * @Date 2022/8/29 10:42
 * @Author Rookie
 */
@Data
@Builder
public class OrganizationMemberDTO {
    @ApiModelProperty(value = "the userId",required = true)
    private String userId;
    @ApiModelProperty(value = "the userName",required = true)
    private String userName;
}
