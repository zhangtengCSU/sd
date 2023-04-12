package com.reach.organization.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_community_pos_meb")
@ApiModel(value = "CommunityPosMeb", description = "a table to record reflection between position and member")
public class CommunityPosMebPO {

    @TableId("id")
    private Long id;

    @TableField("organization_id")
    private String organizationId;

    @TableField("position_id")
    private String positionId;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("operator")
    private String operator;

    @TableField(value = "create_time")
    private Long createTime;


}
