package com.brunobarchesi.ProductGuardian.repository;

import com.brunobarchesi.ProductGuardian.entity.ProductEntity;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    Optional<ProductEntity> findByIdAndUser(UUID id, UserEntity user);
    List<ProductEntity> findAllByUser(UserEntity user);
}
