package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCode;

import java.util.Optional;

public interface ProductService
{

    /**
     * @param   barCode
     * @return  Optional of ProductDTO, is null if product was not found on DB
     */
    Optional<ProductDTO> findByBarCode( BarCode barCode );
}
