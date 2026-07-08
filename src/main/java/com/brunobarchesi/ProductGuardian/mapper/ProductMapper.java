package com.brunobarchesi.ProductGuardian.mapper;

import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductRequestDTO;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductResponseDTO;
import com.brunobarchesi.ProductGuardian.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponseDTO(ProductEntity productEntity);
}
