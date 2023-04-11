package com.reach.os.idea.domain.vo;

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
public class Reply2ReplyVO {
    @ApiModelProperty("reply_id")
    private String reply_id;
    @ApiModelProperty("reply_author")
    private String reply_author;
    @ApiModelProperty("object_id")
    private String reply_content;
    @ApiModelProperty(value = "create_time")
    private String create_time;
    private List<Reply2ReplyVO> reply2ReplyVO;
}
