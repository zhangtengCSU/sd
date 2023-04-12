package com.reach.organization.domain.dto.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class OrganizationCreateDTO {
    @ApiModelProperty(value = "organization basic info",required = true)
    private BasicInfoDTO basic_info_dto;
    @ApiModelProperty(value = "gas amount of operation cost",required = true)
    private String total_amount_cost;
    @ApiModelProperty(value = "current userId",required = true)
    private String user_id;
}
