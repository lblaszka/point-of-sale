package com.github.lblaszka.pointofsale.displaylcd;

import java.math.BigDecimal;

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
        return null;
    }
}
