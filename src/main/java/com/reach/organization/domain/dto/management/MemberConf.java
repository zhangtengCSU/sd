package com.reach.organization.domain.dto.management;

import com.reach.organization.domain.dto.permission.Permission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;


@Data
public class MemberConf {
    /**
     * 1-workflow,2-position
     */
    @ApiModelProperty("1-workflow,2-position")
    private Integer configType;
    private String workflowId;
    private List<Permission> permission;
}
