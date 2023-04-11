package com.reach.os.idea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.reach.auth.service.UserService;
import com.reach.common.utils.DateUtil;
import com.reach.common.utils.IdUtil;
import com.reach.mappers.IdeaTopicMapper;
import com.reach.os.idea.domain.po.IdeaTopic;
import com.reach.os.idea.domain.req.IdeaReqBody;
import com.reach.os.idea.service.IdeaTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class IdeaTopicServiceImpl extends ServiceImpl<IdeaTopicMapper, IdeaTopic> implements IdeaTopicService {
    @Override
    public Boolean saveIdea(IdeaReqBody ideaReqBody) {
        String id = IdUtil.getId();
        IdeaTopic entity = IdeaTopic
                .builder()
                .ideaId(id)
                .ideaAuthor(ideaReqBody.getUser_id())
                .ideaTitle(ideaReqBody.getIdea_title())
                .ideaContent(ideaReqBody.getIdea_content())
                .createTime(DateUtil.currentSystemLongTimeAsString())
                .tags(genSplitStr(ideaReqBody.getTags()))
                .build();
        return this.save(entity);
    }

    private String genSplitStr(List<String> strings) {
        return String.join(",", strings);
    }

    @Override
    public Boolean deleteIdea(String ideaId) {
        return this.remove(
                new QueryWrapper<IdeaTopic>()
                        .lambda()
                        .eq(IdeaTopic::getIdeaId, ideaId)
        );
    }

    @Override
    public List<IdeaTopic> getIdeaList(String userId) {
        return this.list(
                new QueryWrapper<IdeaTopic>()
                        .lambda()
                        .eq(IdeaTopic::getIdeaAuthor, userId)
        );
    }

    @Override
    public IdeaTopic getIdeaById(String ideaId) {
        return this.getById(ideaId);
    }
}
