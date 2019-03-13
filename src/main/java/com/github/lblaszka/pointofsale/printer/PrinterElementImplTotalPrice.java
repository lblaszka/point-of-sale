package com.github.lblaszka.pointofsale.printer;

import java.math.BigDecimal;

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
        return null;
    }
}
