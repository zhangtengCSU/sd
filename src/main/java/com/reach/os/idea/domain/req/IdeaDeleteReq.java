package com.reach.os.idea.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDeleteReq {
    private String user_id;
    private String token;
    private String idea_id;
}
