package com.reach.auth.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2022/9/22 13:50
 * @Author Rookie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginReq {
    private String pk;
    private String sign;
}
