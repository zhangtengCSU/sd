package com.reach.organization.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_organization")
public class Organization {

    @TableId("org_id")
    private String orgId;

    @TableField("org_name")
    private String orgName;

    @TableField("desc")
    private String desc;

    @TableField("logo")
    private String logo;

    @TableField("creator")
    private String creator;


}
