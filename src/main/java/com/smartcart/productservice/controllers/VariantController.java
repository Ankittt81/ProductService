package com.smartcart.productservice.controllers;

import com.smartcart.productservice.dtos.VariantRequestDto;
import com.smartcart.productservice.dtos.VariantResponseDto;
import com.smartcart.productservice.mappers.VariantMapper;
import com.smartcart.productservice.models.Variant;
import com.smartcart.productservice.services.VariantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variant")
public class VariantController {
    private VariantService variantService;
    private VariantMapper  variantMapper;

    public VariantController(VariantService variantService, VariantMapper variantMapper) {
        this.variantService = variantService;
        this.variantMapper = variantMapper;
    }

    @GetMapping("/product/{productId}/variants")
    List<VariantResponseDto> getVariantByProductId(@PathVariable("productId")Long productId){
        return null;
    }

    @PostMapping()
    VariantResponseDto createVariant(@RequestBody VariantRequestDto  variantRequestDto){
        Variant variant=variantService.createVariant(
                variantRequestDto.getProductId(),
                variantRequestDto.getSku(),
                variantRequestDto.getAttributes(),
                variantRequestDto.getPrice());

        return variantMapper.toDto(variant);
    }
    void updateVariant(){

    }
    void deleteVariant(){}
}
