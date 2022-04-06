package com.ShopShoe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponseDto {
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
