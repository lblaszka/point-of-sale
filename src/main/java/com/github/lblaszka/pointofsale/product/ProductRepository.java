package com.github.lblaszka.pointofsale.product;

import java.util.Optional;

public interface ProductRepository
{

    /**
     * Search in DB product by string bar code.
     *
     * @param barCode   String bar code of product
     * @return          Optional with product or null presence thew not found product on DB
     */
    Optional<Product> findByBarCode( String barCode );
}