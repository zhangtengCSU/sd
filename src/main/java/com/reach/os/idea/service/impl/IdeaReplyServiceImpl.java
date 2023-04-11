package com.reach.os.idea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reach.auth.service.UserService;
import com.reach.common.utils.DateUtil;
import com.reach.common.utils.IdUtil;
import com.reach.mappers.IdeaReplyMapper;
import com.reach.os.idea.domain.po.IdeaReply;
import com.reach.os.idea.domain.req.ReplyReqBody;
import com.reach.os.idea.domain.vo.IdeaReplyVO;
import com.reach.os.idea.domain.vo.Reply2ReplyVO;
import com.reach.os.idea.service.IdeaReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaReplyServiceImpl extends ServiceImpl<IdeaReplyMapper, IdeaReply> implements IdeaReplyService {
    @Resource
    private UserService userService;

    @Override
    public Boolean addReply(ReplyReqBody reply) {
        String id = IdUtil.getId();
        IdeaReply build = IdeaReply
                .builder()
                .replyId(id)
                .ideaId(reply.getIdea_id())
                .replyAuthor(reply.getUser_id())
                .reply_content(reply.getReply_content())
                .createTime(DateUtil.currentSystemLongTimeAsString())
                .build();
        return this.save(build);
    }

    @Override
    public Boolean deleteReply(String replyId) {
        return this.remove(new QueryWrapper<IdeaReply>()
                .lambda()
                .eq(IdeaReply::getReplyId,replyId));
    }

    @Override
    public List<IdeaReply> getListByIdeaOrReplyId(String id) {
        List<IdeaReply> ideaList = this.list(
                new QueryWrapper<IdeaReply>()
                        .lambda()
                        .eq(IdeaReply::getIdeaId, id)
        );
        return ideaList;
    }

    @Override
    public List<IdeaReplyVO> getReplyOfIdea(String ideaId) {
        List<IdeaReplyVO> res = new ArrayList<>();
        List<IdeaReply> ideaList = getListByIdeaOrReplyId(ideaId);
        if (CollectionUtils.isEmpty(ideaList)) {
            return null;
        }
        for (IdeaReply reply : ideaList) {
            IdeaReplyVO build = IdeaReplyVO
                    .builder()
                    .reply_id(reply.getReplyId())
                    .reply_author(userService.findUserNameById(reply.getReplyAuthor()))
                    .reply_content(reply.getReply_content())
                    .create_time(reply.getCreateTime())
                    .replys_of_reply(getReplyOfReply(reply.getReplyId()))
                    .build();
            res.add(build);
        }
        return res;
    }

    @Override
    public List<Reply2ReplyVO> getReplyOfReply(String replyId) {
        List<Reply2ReplyVO> res = new ArrayList<>();
        List<IdeaReply> ideaList = getListByIdeaOrReplyId(replyId);
        if (CollectionUtils.isEmpty(ideaList)) {
            return null;
        }
        for (IdeaReply reply : ideaList) {
            Reply2ReplyVO reply2ReplyVO = Reply2ReplyVO
                    .builder()
                    .reply_id(reply.getReplyId())
                    .reply_author(reply.getReplyAuthor())
                    .reply_content(reply.getReply_content())
                    .create_time(reply.getCreateTime())
                    .reply2ReplyVO(getReplyOfReply(reply.getReplyId()))
                    .build();
            res.add(reply2ReplyVO);
        }
        return res;
    }
}
