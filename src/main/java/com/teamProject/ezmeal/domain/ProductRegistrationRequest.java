package com.teamProject.ezmeal.domain;

import java.util.HashMap;
import java.util.List;

/*상품 등록 DTO*/
public class ProductRegistrationRequest {

    private ProductDto productDto;
    private List<ProductOptionDto> productOptionDto;

    public ProductRegistrationRequest(ProductDto productDto, List<ProductOptionDto> productOptionDto) {
        this.productDto = productDto;
        this.productOptionDto = productOptionDto;
    }

    public ProductRegistrationRequest(){}

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public List<ProductOptionDto> getProductOptionDto() {
        return productOptionDto;
    }

    public void setProductOptionDto(List<ProductOptionDto> productOptionDto) {
        this.productOptionDto = productOptionDto;
    }
}
