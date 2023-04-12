package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2022/8/29 10:40
 * @Author Rookie
 */
@Data
@Builder
public class PositionMembersVO {
    @ApiModelProperty("organizationId")
    private String organizationId;
    @ApiModelProperty("positionId")
    private String positionId;
    @ApiModelProperty("positionName")
    private String positionName;
    @ApiModelProperty("positionColor")
    private String color;
    @ApiModelProperty("the members list of the position")
    private List<OrganizationMemberVO> members;
}
