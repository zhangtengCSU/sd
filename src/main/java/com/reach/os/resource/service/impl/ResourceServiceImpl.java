package com.reach.os.resource.service.impl;

import com.reach.mappers.ResourceMapper;
import com.reach.os.resource.domain.po.Resource;
import com.reach.os.resource.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
