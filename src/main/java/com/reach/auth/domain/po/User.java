package com.reach.auth.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_user")
public class User {

    @TableId("public_key")
    private String publicKey;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("logo")
    private String logo;


}
