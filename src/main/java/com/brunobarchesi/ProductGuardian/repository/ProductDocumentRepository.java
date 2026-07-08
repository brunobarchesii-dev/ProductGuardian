package com.brunobarchesi.ProductGuardian.repository;

import com.brunobarchesi.ProductGuardian.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.jar.JarEntry;

public interface ProductDocumentRepository extends JpaRepository<DocumentEntity, UUID> {

}
