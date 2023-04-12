package com.reach.organization.controller;

import com.reach.common.response.ReachResponse;
import com.reach.organization.domain.dto.basic.BasicInfoDTO;
import com.reach.organization.domain.dto.basic.OrganizationCreateDTO;
import com.reach.organization.domain.vo.OrganizationBasicInfoVO;
import com.reach.organization.service.OrganizationManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2022/4/28 13:51
 * @Author Rookie
 */
@Api("OrganizationManagement")
@RestController
@RequestMapping(value = "/organization")
@Slf4j
public class OrganizationManageController {
    @Resource
    private OrganizationManager organizationManager;

    @ApiOperation("call when Register an organization;if success then returns organizationId")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReachResponse<String> registerOrganization(@RequestBody OrganizationCreateDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.createOrganization(dto)).build();
    }

    @ApiOperation("upload a logo then return S3 address where logo was stored")
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file from data"),
            @ApiImplicitParam(name = "imageType", value = "file type: 0-organization,1-user,2-normal,3-asset,here should be 0"),
    })
    public ReachResponse<String> uploadPic(@RequestParam("file") CommonsMultipartFile file, @RequestParam("relatedId") String relatedId, @RequestParam("imageType") Integer imageType, @RequestParam("userId") String userId) {
        return ReachResponse.SUCCEED(organizationManager.uploadImage(file, relatedId, userId)).build();
    }

    @ApiOperation("query organization Basic info")
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public ReachResponse<OrganizationBasicInfoVO> getOrganizationBasic(@RequestParam String organizationId) {
        return ReachResponse.SUCCEED(organizationManager.getOrganizationBasic(organizationId)).build();
    }

    @ApiOperation("update organization Basic info")
    @RequestMapping(value = "/basic", method = RequestMethod.POST)
    public ReachResponse<Boolean> updateOrganizationBasic(@RequestBody BasicInfoDTO dto) {
        return ReachResponse.SUCCEED(organizationManager.updateOrganizationBasic(dto)).build();
    }

}
