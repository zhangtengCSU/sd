package com.reach.os.idea.domain.vo;

import com.reach.os.idea.domain.po.IdeaReply;
import com.reach.os.idea.domain.po.IdeaTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class IdeaVO {
    private IdeaTopicVO idea_body;
    private List<IdeaReplyVO> idea_reply;
}
