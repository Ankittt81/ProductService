package com.smartcart.productservice.controllers;

import com.smartcart.productservice.dtos.UVariantRequestDto;
import com.smartcart.productservice.dtos.VariantRequestDto;
import com.smartcart.productservice.dtos.VariantResponseDto;
import com.smartcart.productservice.mappers.VariantMapper;
import com.smartcart.productservice.models.Variant;
import com.smartcart.productservice.services.VariantService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product/variant")
public class VariantController {
    private VariantService variantService;
    private VariantMapper  variantMapper;

    public VariantController(VariantService variantService, VariantMapper variantMapper) {
        this.variantService = variantService;
        this.variantMapper = variantMapper;
    }

    @GetMapping("/variants")
    List<VariantResponseDto> getVariantByProductId(@RequestParam("productId") Long productId){
        List<Variant> variants=variantService.getVariantsByProductId(productId);
        List<VariantResponseDto> variantResponseDtos=new ArrayList<>();
        for(Variant variant:variants){
           variantResponseDtos.add(variantMapper.toDto(variant));
        }
        return variantResponseDtos;
    }

    @GetMapping("/variantId/{variantId}")
    VariantResponseDto getVariantByVariantId(@PathVariable("variantId") Long variantId){
        Variant variant=variantService.getVariantById(variantId);
        return variantMapper.toDto(variant);
    }

    @GetMapping("/sku/{sku}")
    VariantResponseDto getVariantBySku(@PathVariable("sku") String sku){
        Variant variant=variantService.getVariantBySku(sku);
        return variantMapper.toDto(variant);
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

    @PatchMapping("{variantId}")
    VariantResponseDto updateVariantPrice(@PathVariable("variantId") Long variantId, @RequestBody UVariantRequestDto uVariantRequestDto){
        Variant variant=variantService.updateVariantPrice(variantId,
                uVariantRequestDto.getPrice());
        return variantMapper.toDto(variant);

    }

    @PatchMapping("/{variantId}/status")
    VariantResponseDto UpdateVariantStatus(@PathVariable("variantId") Long variantId,@RequestBody UVariantRequestDto uVariantRequestDto){
        Variant variant=variantService.updateVariantStatus(variantId,uVariantRequestDto.getStatus());
        return variantMapper.toDto(variant);
    }
}
