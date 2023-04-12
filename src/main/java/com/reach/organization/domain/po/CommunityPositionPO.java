package com.reach.organization.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@TableName("t_community_position")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommunityPosition", description = "")
public class CommunityPositionPO {

    @ApiModelProperty("pk")
    @TableId("id")
    private String id;

    @ApiModelProperty("PositionName")
    @TableField("position_name")
    private String positionName;

    @TableField("color")
    private String color;

    @TableField("organizationId")
    private String organizationId;

    @TableField("creator")
    private String creator;

    @TableField(value = "create_time")
    private Long createTime;

    @TableField(value = "status")
    private String status;



}
