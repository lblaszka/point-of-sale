package com.github.lblaszka.pointofsale.product;

public interface ProductConverter
{

    /**
     * Convert Product instance to ProductDTO instance.
     *
     * @throws      ProductConverterException if param is null
     * @param       product to be converter on ProductDTO instance
     * @return      ProductDTO instance converted from Product instance
     */
    ProductDTO convert( Product product ) throws ProductConverterException;

    /**
     * Convert ProductDTO instance to Product instance.
     *
     * @throws      ProductConverterException if param is null
     * @throws      ProductConverterException if BarCodeContainerImpl hes object with not same class as in domain.
     * @param       productDTO to be converter on Product instance
     * @return      Product instance converted from Product instance
     */
    Product convert( ProductDTO productDTO ) throws ProductConverterException;
}
