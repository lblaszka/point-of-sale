package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;

import java.util.Optional;

public class ProductServiceImpl implements ProductService
{
    private ProductRepository productRepository;
    private ProductConverter productConverter;


    public ProductServiceImpl( ProductRepository productRepository, ProductConverter productConverter )
    {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }


    @Override
    public Optional<ProductDTO> findByBarCode( BarCodeContainer barCodeContainer )
    {
        return null;
    }
}
