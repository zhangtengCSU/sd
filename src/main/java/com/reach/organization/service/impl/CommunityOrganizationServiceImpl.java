package com.reach.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reach.organization.domain.po.CommunityOrganizationPO;
import com.reach.mappers.CommunityOrganizationMapper;
import com.reach.organization.service.CommunityOrganizationService;
import org.springframework.stereotype.Service;


@Service
public class CommunityOrganizationServiceImpl extends ServiceImpl<CommunityOrganizationMapper, CommunityOrganizationPO> implements CommunityOrganizationService {

}
