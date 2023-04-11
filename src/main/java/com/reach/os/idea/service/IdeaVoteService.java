package com.reach.os.idea.service;

import com.reach.os.idea.domain.po.IdeaVote;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

public interface IdeaVoteService extends IService<IdeaVote> {
    Integer vote(String ideaId);
    Integer getCount(String ideaId);
}
