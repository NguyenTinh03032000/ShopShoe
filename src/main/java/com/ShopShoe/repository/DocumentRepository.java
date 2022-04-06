package com.ShopShoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ShopShoe.entity.DocumentEntity;


public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer>{
	@Query("Select a from DocumentEntity a where user_id = ?1 and document_type = ?2")
	DocumentEntity checkDocumentByUserId(Integer userId, String docType);
    
    @Query("Select fileName from DocumentEntity a where user_id = ?1 and document_type = ?2")
    String getUploadDocumnetPath(Integer userId, String docType);
}
