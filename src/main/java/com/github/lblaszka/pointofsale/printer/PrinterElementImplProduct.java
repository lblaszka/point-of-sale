package com.github.lblaszka.pointofsale.printer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrinterElementImplProduct implements PrinterElement
{
    private String label;
    private BigDecimal price;


    public PrinterElementImplProduct( String label, BigDecimal price )
    {
        this.label = label;
        this.price = price;
    }


    @Override
    public String getPrintable()
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
