package com.reach.os.idea.controller;

import com.reach.auth.service.UserService;
import com.reach.common.BaseController;
import com.reach.common.response.ReachResponse;
import com.reach.os.idea.domain.req.IdeaPublishReq;
import com.reach.os.idea.domain.req.ReplyAddReq;
import com.reach.os.idea.domain.req.ReplyDeleteReq;
import com.reach.os.idea.service.IdeaReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reply")
@Api(tags = "Reply Idea Controller")
@Slf4j
public class IdeaReplyController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private IdeaReplyService ideaReplyService;

    @Override
    protected Logger getLog() {
        return log;
    }

    @ApiOperation("Add a reply，【!!! will add WEB SOCKET event !!!】")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ReachResponse<Boolean> addReply(@RequestBody ReplyAddReq req) {
        return dealWithException(req, this::doReplyAdd, "Reply-Add");
    }
    private Boolean doReplyAdd(ReplyAddReq req) {
        userService.ifAuth(req.getToken());
        return ideaReplyService.addReply(req.getReply());
    }

    @ApiOperation("Delete a reply")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ReachResponse<Boolean> delReply(@RequestBody ReplyDeleteReq req) {
        return dealWithException(req, this::doReplyDel, "Reply-del");
    }

    private Boolean doReplyDel(ReplyDeleteReq req) {
        userService.ifAuth(req.getToken());
        return ideaReplyService.deleteReply(req.getReply_id());
    }

}
