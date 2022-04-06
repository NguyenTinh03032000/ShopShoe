package com.ShopShoe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer documentId;
    
    @Column(name = "user_id")
    private Integer UserId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "document_type")
    private String documentType;
    
    @Column(name = "document_format")
    private String documentFormat;
    
    @Column(name = "upload_dir")
    private String uploadDir;
}
