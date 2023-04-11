package com.reach.os.idea.service;

import com.reach.os.idea.domain.po.IdeaReply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reach.os.idea.domain.req.ReplyReqBody;
import com.reach.os.idea.domain.vo.IdeaReplyVO;
import com.reach.os.idea.domain.vo.Reply2ReplyVO;

import java.util.List;

public interface IdeaReplyService extends IService<IdeaReply> {
    Boolean addReply(ReplyReqBody reply);

    Boolean deleteReply(String replyId);

    List<IdeaReply> getListByIdeaOrReplyId(String id);

    List<IdeaReplyVO> getReplyOfIdea(String ideaId);

    List<Reply2ReplyVO> getReplyOfReply(String replyId);
}
