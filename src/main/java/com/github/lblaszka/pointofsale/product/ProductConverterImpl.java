package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;

public class ProductConverterImpl implements ProductConverter
{
    @Override
    public ProductDTO convert( Product product ) throws ProductConverterException
    {
        if( product == null )
            throw new ProductConverterException( "ProductConverter: product is null!" );
        return new ProductDTO(
                product.getLabel(),
                new BarCodeContainer( product.getBarCode() ),
                product.getPrice()
        );
    }


    @Override
    public Product convert( ProductDTO productDTO ) throws ProductConverterException
    {
        if( productDTO == null )
            throw new ProductConverterException( "ProductConverter: productDTO is null!" );
        //if( !( productDTO.getBarCodeContainer().getBarCodeObject() instanceof String ) )
        //    throw new ProductConverterException( "ProductConverter: BarCodeContainer has object not same type as barCode in Product class." );

        try
        {
            return new Product(
                    new Long( 0L ),
                    productDTO.getLabel(),
                    (String) productDTO.getBarCodeContainer().getBarCodeObject(),
                    productDTO.getPrice()
            );
        }
        catch ( ClassCastException exc )
        {
            throw new ProductConverterException( "ProductConverter: BarCodeContainer has object not same type as barCode in Product class." );
        }

    }
}
