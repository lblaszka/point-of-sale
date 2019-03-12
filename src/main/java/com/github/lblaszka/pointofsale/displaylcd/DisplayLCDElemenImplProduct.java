package com.github.lblaszka.pointofsale.displaylcd;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        if( label != null && label.length() != 0 )
        {
            if( label.length() > 20 )
            {
                label = new String( label.substring( 0,17 )  +"..." );
            }
        }
        else
        {
            label = "*NO LABEL*";
        }

        if( price != null )
        {
            return String.format( "%-20s : ", label )+price.setScale( 2, RoundingMode.HALF_UP )+" PLN";
        }
        else
        {
            return String.format( "%-20s : ", label )+"N/A";
        }
    }
}
