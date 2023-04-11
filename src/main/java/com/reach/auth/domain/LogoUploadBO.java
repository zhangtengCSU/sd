package com.reach.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogoUploadBO {
    private CommonsMultipartFile file;
    private String userId;
}
