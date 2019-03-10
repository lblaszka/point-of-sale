package com.github.lblaszka.pointofsale.product;

import com.github.lblaszka.pointofsale.barcode.BarCode;

import java.math.BigDecimal;
import java.util.Objects;

public class Product
{
    private Long id;
    private String label;
    private BarCode barCode;
    private BigDecimal price;


    public Product()
    {
    }


    public Product( Long id, String label, BarCode barCode, BigDecimal price )
    {
        this.id = id;
        this.label = label;
        this.barCode = barCode;
        this.price = price;
    }


    public Long getId()
    {
        return id;
    }


    public void setId( Long id )
    {
        this.id = id;
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


    @Override
    public boolean equals( Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Product that = (Product) o;
        return Objects.equals( id, that.id );
    }


    @Override
    public int hashCode()
    {
        return Objects.hash( id );
    }
}
