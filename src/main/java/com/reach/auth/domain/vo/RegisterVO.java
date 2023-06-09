package com.reach.auth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2022/9/22 11:33
 * @Author Rookie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterVO {
    private String user_id;
    private String user_name;
    private String user_public_key;
    private String logo;
    private String token;
}
