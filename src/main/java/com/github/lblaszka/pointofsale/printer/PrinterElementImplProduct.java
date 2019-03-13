package com.github.lblaszka.pointofsale.printer;

import java.math.BigDecimal;

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
        return null;
    }
}
