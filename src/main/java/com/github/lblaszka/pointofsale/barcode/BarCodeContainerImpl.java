package com.github.lblaszka.pointofsale.barcode;


import java.util.Objects;

public class BarCodeContainerImpl implements BarCodeContainer
{
    private Object barCodeObject;


    public BarCodeContainerImpl( Object barCodeObject )
    {
        this.barCodeObject = barCodeObject;
    }

    @Override
    public Object getBarCodeObject()
    {
        return barCodeObject;
    }

    @Override
    public void setBarCodeObject( Object barCodeObject )
    {
        this.barCodeObject = barCodeObject;
    }
}