package com.reach.os.idea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import com.reach.mappers.IdeaVoteMapper;
import com.reach.os.idea.domain.po.IdeaVote;
import com.reach.os.idea.service.IdeaVoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IdeaVoteServiceImpl extends ServiceImpl<IdeaVoteMapper, IdeaVote> implements IdeaVoteService {
    @Override
    public Integer vote(String ideaId) {
        IdeaVote vote = this.getById(ideaId);
        vote.setVote(vote.getVote() + 1);
        if (!this.updateById(vote)) {
            throw ReachException.build(ResponseEnum.OPTIMISTIC_LOCK_EXCEPTION);
        }
        return vote.getVote() + 1;
    }

    @Override
    public Integer getCount(String ideaId) {
        IdeaVote byId = this.getById(ideaId);
        return byId.getVote();
    }
}
