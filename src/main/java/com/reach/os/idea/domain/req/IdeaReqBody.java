package com.reach.os.idea.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaReqBody {
    private String idea_title;
    private String idea_content;
    private String user_id;
    private List<String> tags;
}
