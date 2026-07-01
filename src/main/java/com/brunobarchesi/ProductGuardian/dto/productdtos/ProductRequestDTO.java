package com.brunobarchesi.ProductGuardian.dto.productdtos;

import com.brunobarchesi.ProductGuardian.dto.enums.ProductCategoryEnum;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductRequestDTO(

        @NotBlank(message = "Product name is required.")
        @Size(max = 150, message = "Product name must have a maximum of 150 characters.")
        String name,

        @Size(max = 1000, message = "Description must have a maximum of 1000 characters.")
        String description,

        @NotNull(message = "Category is required.")
        ProductCategoryEnum category,

        @NotBlank(message = "Brand is required.")
        @Size(max = 100, message = "Brand must have a maximum of 100 characters.")
        String brand,

        @Size(max = 100, message = "Model must have a maximum of 100 characters.")
        String model,

        @Size(max = 150, message = "Store name must have a maximum of 150 characters.")
        String storeName,

        @NotNull(message = "Purchase date is required.")
        LocalDate purchaseDate,

        @NotNull(message = "Purchase price is required.")
        @DecimalMin(value = "0.0", inclusive = false, message = "Purchase price must be greater than zero.")
        BigDecimal purchasePrice,

        @NotNull(message = "Warranty months is required.")
        @PositiveOrZero(message = "Warranty months cannot be negative.")
        Integer warrantyMonths,

        @Size(max = 100, message = "Invoice number must have a maximum of 100 characters.")
        String invoiceNumber,

        @Size(max = 2000, message = "Notes must have a maximum of 2000 characters.")
        String notes)

{

}



