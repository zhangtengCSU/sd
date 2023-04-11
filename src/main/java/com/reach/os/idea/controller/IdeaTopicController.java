package com.reach.os.idea.controller;

import com.reach.auth.service.UserService;
import com.reach.common.BaseController;
import com.reach.common.response.ReachResponse;
import com.reach.os.idea.domain.po.IdeaReply;
import com.reach.os.idea.domain.po.IdeaTopic;
import com.reach.os.idea.domain.req.IdeaDeleteReq;
import com.reach.os.idea.domain.req.IdeaDetailReq;
import com.reach.os.idea.domain.req.IdeaListReq;
import com.reach.os.idea.domain.req.IdeaPublishReq;
import com.reach.os.idea.domain.vo.IdeaReplyVO;
import com.reach.os.idea.domain.vo.IdeaTopicVO;
import com.reach.os.idea.domain.vo.IdeaVO;
import com.reach.os.idea.service.IdeaReplyService;
import com.reach.os.idea.service.IdeaTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/idea")
@Api(tags = "Idea Management Controller")
@Slf4j
public class IdeaTopicController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private IdeaTopicService ideaTopicService;
    @Resource
    private IdeaReplyService ideaReplyService;

    @Override
    protected Logger getLog() {
        return log;
    }

    @ApiOperation("Public idea")
    @RequestMapping(value = "/pub", method = RequestMethod.POST)
    public ReachResponse<Boolean> publicIdea(@RequestBody IdeaPublishReq req) {
        return dealWithException(req, this::doIdeaPublic, "Idea-Pub");
    }

    private Boolean doIdeaPublic(IdeaPublishReq req) {
        userService.ifAuth(req.getToken());
        return ideaTopicService.saveIdea(req.getIdea());
    }


    @ApiOperation("Delete idea")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ReachResponse<Boolean> delIdea(@RequestBody IdeaDeleteReq req) {
        userService.ifAuth(req.getToken());
        return dealWithException(req, this::doIdeaDelete, "Idea-del");
    }

    private Boolean doIdeaDelete(IdeaDeleteReq req) {
        userService.ifAuth(req.getToken());
        return ideaTopicService.deleteIdea(req.getIdea_id());
    }

    @ApiOperation("get idea list of own")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ReachResponse<List<IdeaTopicVO>> getIdeaList(@RequestBody IdeaListReq req) {
        userService.ifAuth(req.getToken());
        return dealWithException(req, this::doGetIdeaList, "Idea-List");
    }

    private List<IdeaTopicVO> doGetIdeaList(IdeaListReq req) {
        // 1.verify token
        userService.ifAuth(req.getToken());
        // 2.get ideaList
        List<IdeaTopic> ideaList = ideaTopicService.getIdeaList(req.getUser_id());

        List<IdeaTopicVO> ideaTopicVOS = new ArrayList<>();
        for (IdeaTopic idea : ideaList) {
            IdeaTopicVO build = IdeaTopicVO
                    .builder()
                    .idea_id(idea.getIdeaId())
                    .idea_title(idea.getIdeaTitle())
                    .idea_author(userService.findUserNameById(idea.getIdeaAuthor()))
                    .idea_content(idea.getIdeaContent())
                    .create_time(idea.getCreateTime())
                    .vote_count(0)
                    .tags(idea.getTags())
                    .build();
            ideaTopicVOS.add(build);
        }
        return ideaTopicVOS;
    }

    @ApiOperation("get idea detail by ideaId")
    @RequestMapping(value = "/det", method = RequestMethod.POST)
    public ReachResponse<IdeaVO> getIdeaDetail(@RequestBody IdeaDetailReq req) {
        userService.ifAuth(req.getToken());
        return dealWithException(req, this::doGetDetail, "Idea-Detail");
    }

    private IdeaVO doGetDetail(IdeaDetailReq req) {
        IdeaVO build = IdeaVO.builder().build();
        // 1.verify token
        userService.ifAuth(req.getToken());
        // 2.get idea
        IdeaTopic ideaTopic = ideaTopicService.getIdeaById(req.getIdeaId());
        // 3.get idea reply
        List<IdeaReplyVO> replyOfIdea = ideaReplyService.getReplyOfIdea(req.getIdeaId());
        if (!CollectionUtils.isEmpty(replyOfIdea)) {
            build.setIdea_reply(replyOfIdea);
        }
        IdeaTopicVO ideaBody = IdeaTopicVO
                .builder()
                .idea_id(ideaTopic.getIdeaId())
                .idea_title(ideaTopic.getIdeaTitle())
                .idea_author(userService.findUserNameById(ideaTopic.getIdeaAuthor()))
                .idea_content(ideaTopic.getIdeaContent())
                .create_time(ideaTopic.getCreateTime())
                .vote_count(0)
                .tags(ideaTopic.getTags())
                .build();
        build.setIdea_body(ideaBody);
        return build;
    }


}
