package com.reach.auth.controller;

import com.reach.auth.domain.RegisterVO;
import com.reach.auth.domain.req.LoginReq;
import com.reach.auth.domain.req.RegisterReq;
import com.reach.common.response.ReachResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "User")
public class UserController {
    @ApiOperation("Login with address")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReachResponse<RegisterVO> login(@RequestBody LoginReq req) {
        return ReachResponse.SUCCEED(RegisterVO
                .builder()
                .token("templateToken")
                .user_id("templateUserId")
                .user_name("templateUserName")
                .build()
        ).build();
    }

    @ApiOperation("Register")
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public ReachResponse<RegisterVO> register(@RequestBody RegisterReq req) {
        return ReachResponse.SUCCEED(RegisterVO
                .builder()
                .user_id("templateUserId")
                .user_name("templateUserName")
                .token("templateToken")
                .build()
        ).build();
    }

}
