package com.reach.os.idea.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.reach.os.idea.domain.po.IdeaTopic;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.License;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class IdeaTopicVO {

    @ApiModelProperty("idea_id")
    private String idea_id;

    @ApiModelProperty("idea_author")
    private String idea_author;

    @ApiModelProperty("idea_title")
    private String idea_title;

    @ApiModelProperty("idea_content")
    private String idea_content;

    @ApiModelProperty(value = "create_time")
    private String create_time;

    @ApiModelProperty("vote_count")
    private Integer vote_count;

    @ApiModelProperty("tags")
    private String tags;
}
