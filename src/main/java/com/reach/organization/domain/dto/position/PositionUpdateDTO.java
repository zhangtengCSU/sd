package com.reach.organization.domain.dto.position;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2022/8/31 20:24
 * @Author Rookie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionUpdateDTO {
    @ApiModelProperty("the id of position to be updated")
    private String positionId;
    @ApiModelProperty("the color")
    private String color;
    @ApiModelProperty("the name")
    private String name;
}
