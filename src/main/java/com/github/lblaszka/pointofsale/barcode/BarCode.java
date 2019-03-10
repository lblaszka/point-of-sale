package com.github.lblaszka.pointofsale.barcode;


import java.util.Objects;

public class BarCode
{
    private Object barCodeObject;


    public BarCode( Object barCodeObject )
    {
        this.barCodeObject = barCodeObject;
    }


    public Object getBarCodeObject()
    {
        return barCodeObject;
    }


    public void setBarCodeObject( Object barCodeObject )
    {
        this.barCodeObject = barCodeObject;
    }
}