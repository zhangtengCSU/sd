package com.reach.os.idea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reach.os.idea.domain.po.IdeaTopic;
import com.reach.os.idea.domain.req.IdeaReqBody;

import java.util.List;

public interface IdeaTopicService extends IService<IdeaTopic> {
    Boolean saveIdea(IdeaReqBody ideaReqBody);

    Boolean deleteIdea(String ideaId);

    List<IdeaTopic> getIdeaList(String userId);

    IdeaTopic getIdeaById(String ideaId);
}
