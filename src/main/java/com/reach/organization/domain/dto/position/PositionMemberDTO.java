package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2022/4/28 11:17
 * @Author Rookie
 */
@Data
public class PositionMemberDTO {
    @ApiModelProperty(value = "organizationId",required = true)
    private String organizationId;
    @ApiModelProperty(value = "positionId",required = true)
    private String positionId;
    @ApiModelProperty(value = "the members list of the position",required = true)
    private List<OrganizationMemberDTO> members;
    @ApiModelProperty(value = "the operator Id",required = true)
    private String userId;
}
