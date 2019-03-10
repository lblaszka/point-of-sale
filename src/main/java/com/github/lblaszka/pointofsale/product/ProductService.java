package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;

import java.util.Optional;

public interface ProductService
{

    /**
     * @param   barCodeContainer
     * @return  Optional of ProductDTO, is null if product was not found on DB
     */
    Optional<ProductDTO> findByBarCode( BarCodeContainer barCodeContainer );
}
