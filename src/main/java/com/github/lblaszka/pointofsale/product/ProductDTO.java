package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCode;

import java.math.BigDecimal;

public class ProductDTO
{
    private String label;
    private BarCode barCode;
    private BigDecimal price;


    public ProductDTO( String label, BarCode barCode, BigDecimal price )
    {
        this.label = label;
        this.barCode = barCode;
        this.price = price;
    }


    public String getLabel()
    {
        return label;
    }


    public void setLabel( String label )
    {
        this.label = label;
    }


    public BarCode getBarCode()
    {
        return barCode;
    }


    public void setBarCode( BarCode barCode )
    {
        this.barCode = barCode;
    }


    public BigDecimal getPrice()
    {
        return price;
    }


    public void setPrice( BigDecimal price )
    {
        this.price = price;
    }
}
