package com.reach.auth.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Date 2022/9/22 11:58
 * @Author Rookie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterReq {
    private String address;
    private String userName;
    private String sign;
}
