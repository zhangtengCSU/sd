package com.reach.os.idea.controller;

import com.reach.common.BaseController;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ReachResponse;
import com.reach.common.response.ResponseEnum;
import com.reach.os.idea.domain.req.IdeaPublishReq;
import com.reach.os.idea.domain.req.IdeaVoteReq;
import com.reach.os.idea.service.IdeaVoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RequestMapping("/vote")
@Slf4j
@Api(tags = "Idea Vote Controller")
@RestController
public class IdeaVoteController extends BaseController {
    @Resource
    private IdeaVoteService ideaVoteService;
    @Override
    protected Logger getLog() {
        return log;
    }

    @ApiOperation("Vote-idea  code:601[failed because optimistic lock,need to retry]")
    @RequestMapping(value = "/vt", method = RequestMethod.POST)
    public ReachResponse<Integer> voteIdea(@RequestBody IdeaVoteReq req) {
        return dealWithException(req, this::doVote, "Idea-vote");
    }
    private Integer doVote(IdeaVoteReq req) {
        return ideaVoteService.vote(req.getIdea_id());
    }

}
