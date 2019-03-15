package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainerImpl;

import java.math.BigDecimal;

public class ProductDTO
{
    private String label;
    private BarCodeContainerImpl barCodeContainerImpl;
    private BigDecimal price;


    public ProductDTO( String label, BarCodeContainerImpl barCodeContainerImpl, BigDecimal price )
    {
        this.label = label;
        this.barCodeContainerImpl = barCodeContainerImpl;
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


    public BarCodeContainerImpl getBarCodeContainerImpl()
    {
        return barCodeContainerImpl;
    }


    public void setBarCodeContainerImpl( BarCodeContainerImpl barCodeContainerImpl )
    {
        this.barCodeContainerImpl = barCodeContainerImpl;
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
