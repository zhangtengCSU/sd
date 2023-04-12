package com.reach.organization.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@TableName("t_community_organization")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "CommunityOrganization", description = "basic information of organization")
public class CommunityOrganizationPO {

    @ApiModelProperty("Organization Id")
    @TableId("id")
    private String id;

    @ApiModelProperty("Organization Name")
    @TableField("name")
    private String name;

    @ApiModelProperty("Organization description")
    @TableField("organization_desc")
    private String organizationDesc;

    @TableField("captain")
    private String captain;

    @ApiModelProperty("Logo url on S3")
    @TableField("logo_url")
    private String logoUrl;

    @ApiModelProperty("creator username")
    @TableField("creator")
    private String creator;

    @ApiModelProperty("createTime-timeMillions")
    @TableField(value = "create_time")
    private Long createTime;

    @ApiModelProperty("0-no,1-yes")
    @TableField("is_deleted")
    private Integer isDeleted;


}
