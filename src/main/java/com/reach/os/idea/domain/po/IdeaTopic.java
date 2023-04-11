package com.reach.os.idea.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author rookie
 * @since 2023-04-11 09:51:11
 */
@Getter
@Setter
@TableName("t_idea_topic")
@Builder
public class IdeaTopic {

    @TableId("idea_id")
    private String ideaId;

    @TableField("idea_author")
    private String ideaAuthor;

    @TableField("idea_title")
    private String ideaTitle;

    @TableField("idea_content")
    private String ideaContent;

    @TableField("tags")
    private String tags;

    @TableField(value = "create_time")
    private String createTime;



}
