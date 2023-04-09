package com.reach.os.idea.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_idea_topic")
public class IdeaTopic {

    @TableId("idea_id")
    private String ideaId;

    @TableField("idea_author")
    private String ideaAuthor;

    @TableField("idea_title")
    private String ideaTitle;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    @TableField("object_id")
    private String objectId;


}
