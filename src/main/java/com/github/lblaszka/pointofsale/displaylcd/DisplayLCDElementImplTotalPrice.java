package com.github.lblaszka.pointofsale.displaylcd;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DisplayLCDElementImplTotalPrice implements DisplayLCDElement
{

    private BigDecimal totalPrice;


    public DisplayLCDElementImplTotalPrice( BigDecimal totalPrice )
    {
        this.totalPrice = totalPrice;
    }


    @Override
    public String getRender()
    {
        if( totalPrice != null )
        {
            return String.format( "%-20s : ", "     TOTAL PRICE:" )+totalPrice.setScale( 2, RoundingMode.HALF_UP )+" PLN";
        }
        else
        {
            return String.format( "%-20s : ", "     TOTAL PRICE:" )+"N/A";
        }
    }
}
