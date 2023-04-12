package com.reach.organization.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.reach.auth.domain.po.UserPO;
import com.reach.auth.service.UserService;
import com.reach.common.DataConstant;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import com.reach.common.utils.DateUtil;
import com.reach.common.utils.IdUtil;
import com.reach.common.utils.S3Util;
import com.reach.organization.domain.dto.basic.BasicInfoDTO;
import com.reach.organization.domain.dto.basic.OrganizationCreateDTO;
import com.reach.organization.domain.dto.position.*;
import com.reach.organization.domain.po.CommunityOrganizationPO;
import com.reach.organization.domain.po.CommunityPosMebPO;
import com.reach.organization.domain.po.CommunityPositionPO;
import com.reach.organization.domain.vo.OrganizationBasicInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/8/31 11:20
 * @Author Rookie
 */
@Service
@Slf4j
public class OrganizationManager {
    @Resource
    private CommunityOrganizationService communityOrganizationService;
    @Resource
    private CommunityPositionService communityPositionService;
    @Resource
    private CommunityPosMebService communityPosMebService;
    @Resource
    private UserService userService;


    public String createOrganization(OrganizationCreateDTO dto) {
        String id = IdUtil.getId();
        CommunityOrganizationPO build = CommunityOrganizationPO
                .builder()
                .id(id)
                .name(dto.getBasic_info_dto().getOrganizationName())
                .organizationDesc(dto.getBasic_info_dto().getOrganizationProfile())
                .logoUrl(dto.getBasic_info_dto().getLogoUrl())
                .creator(dto.getUser_id())
                .createTime(DateUtil.currentSystemTimeAsLong())
                .captain(dto.getUser_id())
                .build();
        if (!communityOrganizationService.save(build)) {
            throw ReachException.build(ResponseEnum.ERROR);
        }
        return id;
    }

    /**
     * upload image
     */
    public String uploadImage(CommonsMultipartFile file, String relatedId, String userId) {
        String prefix = "suidemo";
        String filePath = S3Util.uploadToS3(file, prefix, "organization");
        if (StringUtils.isEmpty(filePath)) {
            throw ReachException.build(ResponseEnum.UPLOAD_TO_S3_ERROR);
        }
        return filePath;
    }

    public OrganizationBasicInfoVO getOrganizationBasic(String organizationId) {
        CommunityOrganizationPO organizationPO = communityOrganizationService.getById(organizationId);
        OrganizationBasicInfoVO vo = OrganizationBasicInfoVO
                .builder()
                .organizationName(organizationPO.getName())
                .organizationProfile(organizationPO.getOrganizationDesc())
                .logoUrl(organizationPO.getLogoUrl())
                .build();
        return vo;
    }

    public boolean updateOrganizationBasic(BasicInfoDTO dto) {
        CommunityOrganizationPO po = communityOrganizationService.getById(dto.getOrganizationId());
        po.setName(dto.getOrganizationName());
        po.setOrganizationDesc(dto.getOrganizationProfile());
        return communityOrganizationService.updateById(po);
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean createPosition(PositionCreateDTO dto) {
        String id = IdUtil.getId();
        CommunityPositionPO build = CommunityPositionPO
                .builder()
                .id(id)
                .positionName(dto.getName())
                .color(dto.getRepresentColor())
                .organizationId(dto.getOrganizationId())
                .creator(dto.getUserId())
                .createTime(DateUtil.currentSystemTimeAsLong())
                .build();
        boolean save = communityPositionService.save(build);
        List<OrganizationMemberDTO> members = Optional.ofNullable(dto.getMembers()).orElse(null);
        if (!CollectionUtils.isEmpty(members) && save) {
            boolean b = addMembers(dto.getOrganizationId(), id, members, dto.getUserId());
            return b;
        }
        return save;
    }

    public boolean deletePosition(String positionId) {
        CommunityPositionPO positionPO = communityPositionService.getById(positionId);
        positionPO.setStatus(DataConstant.INVALID);
        return communityPositionService.updateById(positionPO);
    }

    public boolean updatePosition(PositionUpdateDTO dto) {
        CommunityPositionPO byId = communityPositionService.getById(dto.getPositionId());
        byId.setColor(dto.getColor());
        byId.setPositionName(dto.getName());
        return communityPositionService.updateById(byId);
    }

    @Transactional(rollbackFor = ReachException.class)
    public boolean addMembers(PositionMemberDTO dto) {
        return addMembers(dto.getOrganizationId(), dto.getPositionId(), dto.getMembers(), dto.getUserId());
    }


    private boolean addMembers(String organizationId, String positionId, List<OrganizationMemberDTO> members, String operator) {
        for (OrganizationMemberDTO member : members) {
            CommunityPosMebPO communityPosMebPOS = communityPosMebService.getBaseMapper().selectOne(
                    new QueryWrapper<CommunityPosMebPO>()
                            .lambda()
                            .eq(CommunityPosMebPO::getOrganizationId, organizationId)
                            .eq(CommunityPosMebPO::getPositionId, positionId)
                            .eq(CommunityPosMebPO::getUserId, member.getUserId())
            );
            if (!Objects.isNull(communityPosMebPOS)) {
                throw ReachException.build(ResponseEnum.USER_ALREADY_IN_POSITION);
            }
            CommunityPosMebPO build = CommunityPosMebPO
                    .builder()
                    .organizationId(organizationId)
                    .positionId(positionId)
                    .userId(member.getUserId())
                    .userName(member.getUserName())
                    .operator(operator)
                    .build();
            if (!communityPosMebService.save(build)) {
                log.error("Error Occurred when Insert to Table [t_community_pos_meb],PO:{}", JSONUtil.toJsonStr(build));
                throw ReachException.build(ResponseEnum.ERROR);
            }
        }
        return true;
    }

    public boolean delMember(PositionMemberDelDTO dto) {
        String positionId = dto.getPositionId();
        if ("".equals(positionId)) {
            positionId = "0";
        }
        CommunityPosMebPO po = communityPosMebService
                .getBaseMapper()
                .selectOne(new QueryWrapper<CommunityPosMebPO>()
                        .lambda()
                        .eq(CommunityPosMebPO::getOrganizationId, dto.getOrganizationId())
                        .eq(CommunityPosMebPO::getPositionId, positionId)
                        .eq(CommunityPosMebPO::getUserId, dto.getUserId())
                );
        if (Objects.isNull(po)) {
            throw ReachException.build(ResponseEnum.MEMBER_ALREADY_BEEN_DELETED);
        }
        return communityPosMebService.removeById(po.getId());
    }

    public List<PositionMembersVO> getPositionAndMembers(String organizationId) {
        List<PositionMembersVO> res = new ArrayList<>();
        // 1.find positions
        List<CommunityPositionPO> positions = communityPositionService
                .getBaseMapper()
                .selectList(new QueryWrapper<CommunityPositionPO>()
                        .lambda()
                        .eq(CommunityPositionPO::getOrganizationId, organizationId)
                        .eq(CommunityPositionPO::getStatus, DataConstant.VALID)
                        .orderByAsc(CommunityPositionPO::getId)
                );
        if (CollectionUtils.isEmpty(positions)) {
            // 2.default captain
            res = addCaptain(organizationId);
            return res;
        }
        // 2.add default captain
        res = addCaptain(organizationId);

        // 3.add members to each position
        for (CommunityPositionPO position : positions) {
            List<CommunityPosMebPO> members = communityPosMebService
                    .getBaseMapper()
                    .selectList(
                            new QueryWrapper<CommunityPosMebPO>()
                                    .lambda()
                                    .eq(CommunityPosMebPO::getPositionId, position.getId())
                                    .orderByAsc(CommunityPosMebPO::getCreateTime)
                    );
            List<OrganizationMemberVO> vos = BeanUtil.copyToList(members, OrganizationMemberVO.class);
            // 4. user rpc for userInfo
            List<UserPO> userInfoByUserId = userService.findUserByIds(vos.stream().map(OrganizationMemberVO::getUserId).collect(Collectors.toList()));
            for (OrganizationMemberVO vo : vos) {
                List<String> logos = userInfoByUserId.stream().filter(user -> user.getUserId().equals(vo.getUserId())).map(UserPO::getLogo).collect(Collectors.toList());
                vo.setLogo(logos.get(0));
            }
            PositionMembersVO voUlti = PositionMembersVO
                    .builder()
                    .organizationId(organizationId)
                    .positionId(position.getId())
                    .positionName(position.getPositionName())
                    .color(position.getColor())
                    .members(vos)
                    .build();
            res.add(voUlti);
        }
        return res;
    }

    private List<PositionMembersVO> addCaptain(String organizationId) {
        List<PositionMembersVO> res = new ArrayList<>();
        CommunityOrganizationPO byId = communityOrganizationService.getById(organizationId);
        UserPO captainInfo = userService.findUserById(byId.getCaptain());
        OrganizationMemberVO build = OrganizationMemberVO
                .builder()
                .userId(byId.getCaptain())
                .userName(captainInfo.getUserName())
                .positionId("10000")
                .logo(captainInfo.getLogo())
                .build();
        PositionMembersVO captain = PositionMembersVO
                .builder()
                .organizationId(organizationId)
                .positionName("Captain")
                .positionId("10000")
                .color("")
                .members(Lists.newArrayList(build))
                .build();
        res.add(captain);
        return res;
    }

    public boolean transferCaptain(CaptainTransferDTO dto) {
        CommunityOrganizationPO organizationPO = communityOrganizationService.getById(dto.getOrganizationId());
        // todo check is organization Members
        CommunityPosMebPO communityPosMebPO = communityPosMebService.getBaseMapper().selectOne(new QueryWrapper<CommunityPosMebPO>()
                .lambda()
                .eq(CommunityPosMebPO::getUserId, dto.getToUserId())
                .eq(CommunityPosMebPO::getOrganizationId, dto.getOrganizationId())
        );
        if (Objects.isNull(communityPosMebPO)) {
            throw ReachException.build(ResponseEnum.CUSTOM, "User:" + dto.getToUserId() + " is NOT a member of organization:" + dto.getOrganizationId());
        }
        organizationPO.setCaptain(dto.getToUserId());
        return communityOrganizationService.updateById(organizationPO);
    }

    public List<PositionMembersVO> getAllMembersOfOrganization(String organizationId) {
        List<PositionMembersVO> positionAndMembers = getPositionAndMembers(organizationId);

        List<CommunityPosMebPO> noPositionMembers = communityPosMebService
                .getBaseMapper()
                .selectList(
                        new QueryWrapper<CommunityPosMebPO>()
                                .lambda()
                                .eq(CommunityPosMebPO::getOrganizationId, organizationId)
                                .orderByAsc(CommunityPosMebPO::getCreateTime))
                .stream()
                .filter(e -> "0".equals(e.getPositionId()))
                .collect(Collectors.toList());
        List<OrganizationMemberVO> vos = BeanUtil.copyToList(noPositionMembers, OrganizationMemberVO.class);

        List<UserPO> userInfoByUserId = userService.findUserByIds(noPositionMembers.stream().map(CommunityPosMebPO::getUserId).collect(Collectors.toList()));

        for (OrganizationMemberVO vo : vos) {
            List<String> logos = userInfoByUserId
                    .stream()
                    .filter(user -> user.getUserId().equals(vo.getUserId()))
                    .map(UserPO::getLogo)
                    .collect(Collectors.toList());
            vo.setLogo(Optional.ofNullable(logos.get(0)).orElse(""));
            vo.setPositionId("");
        }

        PositionMembersVO build = PositionMembersVO.builder().positionId("").positionName("").organizationId(organizationId).color("").members(vos).build();
        positionAndMembers.add(build);
        return positionAndMembers;
    }


}
