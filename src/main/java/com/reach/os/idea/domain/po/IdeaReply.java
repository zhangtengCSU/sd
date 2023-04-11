package com.reach.os.idea.domain.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@TableName("t_idea_reply")
public class IdeaReply {

    @TableId("reply_id")
    private String replyId;

    @TableField("idea_id")
    private String ideaId;

    @TableField("reply_author")
    private String replyAuthor;

    @TableField(value = "create_time")
    private String createTime;

    @TableField("reply_content")
    private String reply_content;


}
