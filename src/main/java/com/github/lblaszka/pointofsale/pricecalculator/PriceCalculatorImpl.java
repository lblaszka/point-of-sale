package com.github.lblaszka.pointofsale.pricecalculator;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculatorImpl implements PriceCalculator
{
    @Override
    public BigDecimal getTotalPrice( List<BigDecimal> priceList )
    {
        if( priceList == null )
            return new BigDecimal( 0 );

        BigDecimal totalPrice = new BigDecimal( 0 );

        for( BigDecimal price : priceList )
        {
            if( price == null )
                continue;

            totalPrice = totalPrice.add( price );
        }

        return totalPrice;
    }
}
