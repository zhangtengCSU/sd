package com.reach.organization.service.impl;

import com.reach.mappers.OrganizationMapper;
import com.reach.organization.domain.po.Organization;
import com.reach.organization.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

}
