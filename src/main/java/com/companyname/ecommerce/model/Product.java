package com.companyname.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "productImg")
@Schema(description = "Represents a product in the e-commerce system")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the product", example = "1")
    private Integer productId;

    @NotBlank(message = "Product's name is required.")
    @Schema(description = "Name of the product", example = "iPhone 15")
    private String productName;

    @NotBlank(message = "Product's description is required.")
    @Schema(description = "Description of the product", example = "Bright screen, great camera, fast, and USB-C")
    private String productDesc;

    @NotBlank(message = "Product's brand is required.")
    @Schema(description = "Brand of the product", example = "Apple")
    private String productBrand;

    @NotNull
    @Min(value = 1, message = "Product's price can't be negative or zero.")
    @Schema(description = "Price of the product", example = "529")
    private Integer productPrice;

    @NotBlank(message = "Product's category is required.")
    @Schema(description = "Category of the product", example = "Mobile")
    private String productCategory;

    @NotNull(message = "Product's release date is required.")
    @Schema(description = "Release date of the product (yyyy-MM-dd)", example = "2023-10-01")
    private LocalDate productReleaseDate;

    @NotNull(message = "Product's availability is required.")
    @Schema(description = "Availability status of the product", example = "true")
    private Boolean productAvailability;

    @NotNull
    @Min(value = 0, message = "Product's quantity can't be negative.")
    @Schema(description = "Available quantity in stock", example = "50")
    private Integer productQuantity;

    @Schema(description = "Image file name", example = "iphone15.jpg")
    private String productImgName;

    @Schema(description = "Image file type", example = "image/jpeg")
    private String productImgType;

    @JsonIgnore
    @Lob
    private byte[] productImg;
}
