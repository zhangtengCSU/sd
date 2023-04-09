package com.reach.os.resource.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_resource")
public class Resource {

    @TableId("resource_id")
    private String resourceId;

    @TableField("resource_name")
    private String resourceName;

    @TableField("resource_abbr")
    private String resourceAbbr;

    @TableField("logo")
    private String logo;

    @TableField("org_id")
    private String orgId;


}
