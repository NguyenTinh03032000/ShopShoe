package com.ShopShoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ShopShoe.dto.UploadFileResponseDto;
import com.ShopShoe.service.DocumentService;

@RestController
@RequestMapping("document")
public class DocumentController {
	
	@Autowired
    private DocumentService documneStorageService;
    
    @PostMapping("/uploadFile")
    @PreAuthorize("hasRole('ADMIN')")
    public UploadFileResponseDto uploadFile(@RequestParam("file") MultipartFile file, 
            @RequestParam("userId") Integer UserId,
            @RequestParam("docType") String docType) {
        String fileName = documneStorageService.storeFile(file, UserId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseDto(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
}
