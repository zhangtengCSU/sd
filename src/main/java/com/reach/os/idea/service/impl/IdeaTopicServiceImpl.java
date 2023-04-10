package com.reach.os.idea.service.impl;

import com.reach.mappers.IdeaTopicMapper;
import com.reach.os.idea.domain.po.IdeaTopic;
import com.reach.os.idea.service.IdeaTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class IdeaTopicServiceImpl extends ServiceImpl<IdeaTopicMapper, IdeaTopic> implements IdeaTopicService {

}
