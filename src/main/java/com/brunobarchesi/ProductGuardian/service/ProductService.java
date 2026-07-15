package com.brunobarchesi.ProductGuardian.service;

import com.brunobarchesi.ProductGuardian.dto.enums.WarrantyStatusEnum;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductRequestDTO;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.productdtos.ProductUpdateDTO;
import com.brunobarchesi.ProductGuardian.entity.ProductEntity;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import com.brunobarchesi.ProductGuardian.exception.ResourceNotFoundException;
import com.brunobarchesi.ProductGuardian.mapper.ProductMapper;
import com.brunobarchesi.ProductGuardian.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    //Criar produto
    public ProductResponseDTO create(ProductRequestDTO dto, UserEntity currentUser) {
        ProductEntity product = productMapper.toEntity(dto);
        product.setUser(currentUser);
        product.setWarrantyEndDate(calculateWarrantyEndDate(product.getPurchaseDate(), product.getWarrantyMonths()));
        product.setWarrantyStatus(getWarrantyStatus(product.getWarrantyEndDate()));
        ProductEntity saved = productRepository.save(product);
        return productMapper.toResponseDTO(saved);
    }


    //Listar produtos do usuario
    public List<ProductResponseDTO> findMyProducts(UserEntity currentUser) {
        return productRepository.findAllByUser(currentUser)
                .stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }


    //Procurar produto pelo id
    public ProductResponseDTO findMyProductById(UUID id, UserEntity currentUser) {
        ProductEntity product = productRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));
        return productMapper.toResponseDTO(product);
    }


    //Atualizar produto
    public ProductResponseDTO update(UUID id, ProductUpdateDTO dto, UserEntity currentUser) {
        ProductEntity product = productRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

        if (dto.name() != null) product.setName(dto.name());
        if (dto.description() != null) product.setDescription(dto.description());
        if (dto.category() != null) product.setCategory(dto.category());
        if (dto.brand() != null) product.setBrand(dto.brand());
        if (dto.model() != null) product.setModel(dto.model());
        if (dto.storeName() != null) product.setStoreName(dto.storeName());
        boolean warrantyDataChanged = false;
        if (dto.purchaseDate() != null) {
            product.setPurchaseDate(dto.purchaseDate());
            warrantyDataChanged = true;
        }
        if (dto.warrantyMonths() != null){
            product.setWarrantyMonths(dto.warrantyMonths());
            warrantyDataChanged = true;
        }
        if (warrantyDataChanged){
            product.setWarrantyEndDate(calculateWarrantyEndDate(product.getPurchaseDate(), product.getWarrantyMonths()));
            product.setWarrantyStatus(getWarrantyStatus(product.getWarrantyEndDate()));
        }
        if (dto.purchasePrice() != null) product.setPurchasePrice(dto.purchasePrice());
        if (dto.invoiceNumber() != null) product.setInvoiceNumber(dto.invoiceNumber());
        if (dto.notes() != null) product.setNotes(dto.notes());


        ProductEntity saved = productRepository.save(product);
        return productMapper.toResponseDTO(saved);
    }


    //Deletar produto
    public void delete(UUID id, UserEntity currentUser) {
        ProductEntity product = productRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado"));

        productRepository.delete(product);
    }



    //Calcular data final da garantia
    public LocalDate calculateWarrantyEndDate(LocalDate purchaseDate, Integer warrantyMonths) {
        return purchaseDate.plusMonths(warrantyMonths);
    }



    //Determinar o status da garantia
    public WarrantyStatusEnum getWarrantyStatus(LocalDate warrantyEndDate) {
        LocalDate now = LocalDate.now();

        if (now.isAfter(warrantyEndDate)) {
            return WarrantyStatusEnum.EXPIRED;
        }

        if (now.isAfter(warrantyEndDate.minusMonths(1))) {
            return WarrantyStatusEnum.EXPIRING_SOON;
        }
        return WarrantyStatusEnum.ACTIVE;
    }
}

