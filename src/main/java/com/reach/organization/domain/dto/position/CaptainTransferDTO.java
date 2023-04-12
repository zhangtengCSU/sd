package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Date 2022/8/29 11:09
 * @Author Rookie
 */
@Data
public class CaptainTransferDTO {
    @ApiModelProperty(value = "organizationId",required = true)
    private String organizationId;
    @ApiModelProperty(value = "the user that expected to be Captain",required = true)
    private String toUserId;
}
