package com.github.lblaszka.pointofsale.displaylcd;

import java.math.BigDecimal;

public class DisplayLCDElemenImplProduct implements DisplayLCDElement
{

    private String label;
    private BigDecimal price;


    public DisplayLCDElemenImplProduct( String label, BigDecimal price )
    {
        this.label = label;
        this.price = price;
    }


    @Override
    public String getRender()
    {
        return null;
    }
}
