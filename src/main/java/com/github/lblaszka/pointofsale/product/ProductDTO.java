package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;

import java.math.BigDecimal;

public class ProductDTO
{
    private String label;
    private BarCodeContainer barCodeContainer;
    private BigDecimal price;


    public ProductDTO( String label, BarCodeContainer barCodeContainer, BigDecimal price )
    {
        this.label = label;
        this.barCodeContainer = barCodeContainer;
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


    public BarCodeContainer getBarCodeContainer()
    {
        return barCodeContainer;
    }


    public void setBarCodeContainer( BarCodeContainer barCodeContainer )
    {
        this.barCodeContainer = barCodeContainer;
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
