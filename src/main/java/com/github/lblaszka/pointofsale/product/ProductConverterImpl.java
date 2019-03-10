package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCode;

public class ProductConverterImpl implements ProductConverter
{
    @Override
    public ProductDTO convert( Product product ) throws ProductConverterException
    {
        if( product == null )
            throw new ProductConverterException( "ProductConverter: product is null!" );
        return new ProductDTO(
                product.getLabel(),
                new BarCode( product.getBarCode() ),
                product.getPrice()
        );
    }


    @Override
    public Product convert( ProductDTO productDTO ) throws ProductConverterException
    {
        if( productDTO == null )
            throw new ProductConverterException( "ProductConverter: productDTO is null!" );

        if( !( productDTO.getBarCode().getBarCodeObject() instanceof String ) )
            throw new ProductConverterException( "ProductConverter: BarCode object is not String class!" );

        return new Product(
                new Long( 0L ),
                productDTO.getLabel(),
                (String) productDTO.getBarCode().getBarCodeObject(),
                productDTO.getPrice()
        );
    }
}
