package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Date 2022/4/28 12:05
 * @Author Rookie
 */
@Data
@Builder
public class PositionCreateDTO {
    @ApiModelProperty(value = "position name",required = true)
    private String name;
    @ApiModelProperty(value = "position color,enums decided by @Peter",required = true)
    private String representColor;
    @ApiModelProperty(value = "owns to which organization",required = true)
    private String organizationId;
    @ApiModelProperty(value = "member initialize",required = false)
    private List<OrganizationMemberDTO> members;
    @ApiModelProperty(value = "the creator Id",required = true)
    private String userId;
}
