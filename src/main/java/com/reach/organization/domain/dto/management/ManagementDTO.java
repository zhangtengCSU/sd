package com.reach.organization.domain.dto.management;

import lombok.Data;

import java.util.List;


@Data
public class ManagementDTO {
    private List<ProjectConf> projectConfList;
    private List<AssetConf> assetConfList;
    private List<MemberConf> memberConfList;
}
