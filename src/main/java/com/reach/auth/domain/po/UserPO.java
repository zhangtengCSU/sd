package com.reach.auth.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_user")
@Builder
public class UserPO {

    @TableId("public_key")
    private String publicKey;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("logo")
    private String logo;


}
