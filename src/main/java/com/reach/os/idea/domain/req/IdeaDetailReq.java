package com.reach.os.idea.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class IdeaDetailReq {
    private String ideaId;
    private String token;
}
