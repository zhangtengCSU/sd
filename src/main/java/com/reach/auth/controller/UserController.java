package com.reach.auth.controller;

import com.reach.auth.domain.LogoUploadBO;
import com.reach.auth.domain.po.UserPO;
import com.reach.auth.domain.vo.LoginVO;
import com.reach.auth.domain.vo.RegisterVO;
import com.reach.auth.domain.req.LoginReq;
import com.reach.auth.domain.req.RegisterReq;
import com.reach.auth.service.UserService;
import com.reach.common.BaseController;
import com.reach.common.response.ReachResponse;
import com.reach.common.response.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@Api(tags = "User")
@Slf4j
public class UserController extends BaseController {
    @Override
    protected Logger getLog() {
        return log;
    }

    @Resource
    private UserService userService;

    @ApiOperation("Login with address")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReachResponse<LoginVO> login(@RequestBody LoginReq req) {
        return dealWithException(req,this::doLogin,"User-Login");
    }

    private LoginVO doLogin(LoginReq req) {
        String token = userService.auth(req.getPk());
        UserPO userByPublicKey = userService.findUserByPublicKey(req.getPk());
        return LoginVO.builder()
                .token(token)
                .user_id(userByPublicKey.getUserId())
                .user_name(userByPublicKey.getUserName())
                .userLogo(userByPublicKey.getLogo())
                .build();
    }

    @ApiOperation("Register")
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public ReachResponse<RegisterVO> register(@RequestBody RegisterReq req) {
        return dealWithException(req, this::doRegister, "User-Register");
    }

    private RegisterVO doRegister(RegisterReq req) {
        return userService.register(req.getPublic_key(), req.getUser_name());
    }

    @ApiOperation("Upload-User-Logo")
    @RequestMapping(value = "/logo", method = RequestMethod.POST)
    public ReachResponse<String> uploadLogo(@RequestParam("file") CommonsMultipartFile file,@RequestParam("userId") String userId) {
        LogoUploadBO uploadBO = LogoUploadBO.builder().file(file).userId(userId).build();
        return dealWithException(uploadBO, userService::uploadLogo, "User-Register");
    }

    @ApiOperation("test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ReachResponse<ResponseEnum> test() {
        return ReachResponse.SUCCEED(ResponseEnum.SUCCESS).build();
    }

}
