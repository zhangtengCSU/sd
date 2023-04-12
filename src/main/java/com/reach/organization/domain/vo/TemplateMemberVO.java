package com.reach.organization.domain.vo;

import com.reach.organization.domain.dto.management.ManagementDTO;
import com.reach.organization.domain.dto.position.PositionCreateDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @Description
 * @Date 2022/4/28 11:22
 * @Author Rookie
 */
@Data
public class TemplateMemberVO {
    @ApiModelProperty("what management rules the template had, will apply to Management Tab Bar")
    private ManagementDTO managementDTO;
    @ApiModelProperty("positions the template contained,will apply to Position and member Tab Bar")
    private List<PositionCreateDTO> positionCreateDTOList;
}
