package com.github.lblaszka.pointofsale.printer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrinterElementImplTotalPrice implements PrinterElement
{
    private BigDecimal totalPrice;


    public PrinterElementImplTotalPrice( BigDecimal totalPrice )
    {
        this.totalPrice = totalPrice;
    }


    @Override
    public String getPrintable()
    {
        if( totalPrice != null )
        {
            return String.format( "%20s  ", "     TOTAL PRICE" )+totalPrice.setScale( 2, RoundingMode.HALF_UP )+" PLN";
        }
        else
        {
            return String.format( "%20s  ", "     TOTAL PRICE" )+"N/A";
        }
    }
}
