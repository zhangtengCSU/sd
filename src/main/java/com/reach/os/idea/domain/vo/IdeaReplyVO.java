package com.reach.os.idea.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class IdeaReplyVO {

    @ApiModelProperty("reply_id")
    private String reply_id;
    @ApiModelProperty("reply_author")
    private String reply_author;
    @ApiModelProperty("reply_content")
    private String reply_content;
    @ApiModelProperty(value = "create_time")
    private String create_time;
    @ApiModelProperty(value = "reply of reply")
    private List<Reply2ReplyVO> replys_of_reply;

}
