package com.reach.os.idea.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_idea_reply")
public class IdeaReply {

    @TableField("reply_id")
    private String replyId;

    @TableId("idea_id")
    private String ideaId;

    @TableField("reply_author")
    private String replyAuthor;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    @TableField("object_id")
    private String objectId;


}
