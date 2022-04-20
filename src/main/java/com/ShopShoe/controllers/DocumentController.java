package com.ShopShoe.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ShopShoe.dto.MessageResponseDto;
import com.ShopShoe.dto.UploadFileResponseDto;
import com.ShopShoe.service.DocumentService;

@RestController
@RequestMapping("document")
public class DocumentController {

	@Autowired
    private DocumentService documneService;
    
	/**
	 * @note api upload file to server
	 * @param file
	 * @param UserId
	 * @param docType
	 * @return
	 */
    @PostMapping("/uploadFile")
    @PreAuthorize("hasRole('ADMIN')")
    public UploadFileResponseDto uploadFile(@RequestParam("file") MultipartFile file, 
            @RequestParam("userId") Integer UserId,
            @RequestParam("docType") String docType) {
        String fileName = documneService.storeFile(file, UserId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseDto(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    /**
     * 
     * @param imageName
     * @return 
     * @throws IOException
     * @note support load image browser
     */
    @GetMapping(value = "/loadFile/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@Validated @PathVariable(value = "imageName") String imageName) throws IOException {
        try {
        	File serverFile = new File("src/main/resources/upload/" + imageName);

        	return ResponseEntity.ok(Files.readAllBytes(serverFile.toPath()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponseDto("Error"));
		}
    }
}
