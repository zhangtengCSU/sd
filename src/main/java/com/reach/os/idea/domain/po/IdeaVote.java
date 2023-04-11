package com.reach.os.idea.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@TableName("t_idea_vote")
@ApiModel(value = "IdeaVote", description = "")
public class IdeaVote {

    @TableId("idea_id")
    private String ideaId;

    @TableField("version")
    @Version
    private Long version;

    @TableField("vote")
    private Integer vote;


}
