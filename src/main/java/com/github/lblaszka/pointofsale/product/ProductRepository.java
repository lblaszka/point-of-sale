package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCode;

import java.util.Optional;

public interface ProductRepository
{

    /**
     * Search in DB product by bar code.
     *
     * @param barCode   BarCode of product
     * @return          Optional with product or null presence thew not found product on DB
     */
    Optional<Product> findByBarCode( BarCode barCode );
}