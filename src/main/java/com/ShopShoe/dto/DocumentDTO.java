package com.ShopShoe.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    private Integer documentId;

    private Integer UserId;

    private String fileName;

    private String documentType;

    private String documentFormat;
    
    private String uploadDir;
}
