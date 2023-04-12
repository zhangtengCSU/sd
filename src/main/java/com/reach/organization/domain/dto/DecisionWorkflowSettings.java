package com.reach.organization.domain.dto;

import com.reach.organization.domain.dto.basic.BasicInfoDTO;
import com.reach.organization.domain.dto.decision.DecisionMakingDTO;
import com.reach.organization.domain.dto.management.ManagementDTO;
import com.reach.organization.domain.dto.position.PositionMemberDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

/**
 * @Description
 * @Date 2022/4/28 12:33
 * @Author Rookie
 */
@Data
public class DecisionWorkflowSettings {
    @ApiModelProperty(value = "Project Basic Info",required = true)
    private BasicInfoDTO basicInfoDTO;
    @ApiModelProperty("Position and Member")
    private List<PositionMemberDTO> positionMemberDTO;
    @ApiModelProperty("Decision-Making")
    private List<DecisionMakingDTO> decisionMakingDTO;
    @ApiModelProperty("Management")
    private ManagementDTO managementDTO;
}
