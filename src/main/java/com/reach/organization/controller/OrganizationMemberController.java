package com.reach.organization.controller;

import com.reach.common.response.ReachResponse;
import com.reach.organization.domain.dto.position.PositionMembersVO;
import com.reach.organization.service.OrganizationManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Date 2022/8/29 14:05
 * @Author Rookie
 */
@Api("MemberManagement")
@RestController
@RequestMapping(value = "/member")
public class OrganizationMemberController {
    @Resource
    private OrganizationManager organizationManager;

    @ApiOperation("getAllMembers,you can group by position OR filter members if has some position")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ReachResponse<List<PositionMembersVO>> getAllMembers(@RequestParam String organizationId) {
        return ReachResponse.SUCCEED(organizationManager.getAllMembersOfOrganization(organizationId)).build();
    }
}
