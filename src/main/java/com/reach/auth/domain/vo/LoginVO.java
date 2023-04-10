package com.reach.auth.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVO {
    @ApiModelProperty("id of user")
    private String user_id;
    @ApiModelProperty("name of user")
    private String user_name;
    @ApiModelProperty("token generated after login which will expired in 1 hour")
    private String token;
    @ApiModelProperty
    private String userLogo;
}
