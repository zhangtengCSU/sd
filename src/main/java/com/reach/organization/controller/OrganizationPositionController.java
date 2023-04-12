package com.reach.organization.controller;

import com.reach.common.response.ReachResponse;
import com.reach.organization.domain.dto.position.*;
import com.reach.organization.service.OrganizationManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Date 2022/8/29 10:28
 * @Author Rookie
 */
@Api("PositionManagement")
@RestController
@RequestMapping(value = "/pos")
public class OrganizationPositionController {
    @Resource
    private OrganizationManager organizationManager;

    @ApiOperation("create a Position")
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ReachResponse<Boolean> createPosition(@RequestBody PositionCreateDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.createPosition(dto)).build();
    }

    @ApiOperation("delete a Position")
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public ReachResponse<Boolean> deletePosition(@RequestParam String positionId) {
        return ReachResponse.SUCCEED(organizationManager.deletePosition(positionId)).build();
    }

    @ApiOperation("update a Position")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ReachResponse<Boolean> updatePosition(@RequestBody PositionUpdateDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.updatePosition(dto)).build();
    }

    @ApiOperation("getMembersListOfPositions")
    @RequestMapping(value = "/members",method = RequestMethod.GET)
    public ReachResponse<List<PositionMembersVO>> getMembers(@RequestParam String organizationId) {
        return ReachResponse.SUCCEED(organizationManager.getPositionAndMembers(organizationId)).build();
    }

    @ApiOperation("addMembersToPosition")
    @RequestMapping(value = "/members",method = RequestMethod.POST)
    public ReachResponse<Boolean> addMembers(@RequestBody PositionMemberDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.addMembers(dto)).build();
    }

    @ApiOperation("deleteMembersFromPosition")
    @RequestMapping(value = "/members",method = RequestMethod.DELETE)
    public ReachResponse<Boolean> delMembers(@RequestBody PositionMemberDelDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.delMember(dto)).build();
    }

    @ApiOperation("transferCaptainToOther")
    @RequestMapping(value = "/captain",method = RequestMethod.POST)
    public ReachResponse<Boolean> transferCaptain(@RequestBody CaptainTransferDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.transferCaptain(dto)).build();
    }
}
