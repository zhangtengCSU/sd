package com.reach.organization.domain.dto.permission;

import com.reach.organization.domain.dto.position.PositionCreateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;


@Data
public class Permission {
    private String id;
    private String name;
    /**
     * 1-project,2-asset,3-member
     */
    @ApiModelProperty("1-project,2-asset,3-member")
    private Integer object;
    /**
     * operation enums code of object
     */
    @ApiModelProperty("operation enums code of object")
    private Integer operation;
    private List<PositionCreateDTO> positionCreateDTO;
}
