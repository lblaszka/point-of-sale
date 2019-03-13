package com.github.lblaszka.pointofsale.pricecalculator;

import java.math.BigDecimal;
import java.util.List;

public interface PriceCalculator
{


    /**
     * @param priceList     list of prices
     * @return              total price
     */
    BigDecimal getTotalPrice( List<BigDecimal> priceList );
}
