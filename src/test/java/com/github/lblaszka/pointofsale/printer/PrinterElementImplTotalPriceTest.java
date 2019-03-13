package com.github.lblaszka.pointofsale.printer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith( MockitoJUnitRunner.class )
public class PrinterElementImplTotalPriceTest
{
    @Test
    public void getPritableWithCorrectPrice()
    {
        //GET
        BigDecimal totalPrice = new BigDecimal( 21f );
        String expectedPrintable = getCorrectPrintable( totalPrice );


        //WHEN
        String printable = new PrinterElementImplTotalPrice( totalPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPritableWithoutPrice()
    {
        //GET
        BigDecimal totalPrice = null;
        String expectedPrintable = getCorrectPrintable( totalPrice );


        //WHEN
        String printable = new PrinterElementImplTotalPrice( totalPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPritableWithRoundedPrice()
    {
        //GET
        BigDecimal totalPrice = new BigDecimal( 21.24534f );
        String expectedPrintable = getCorrectPrintable( totalPrice );


        //WHEN
        String printable = new PrinterElementImplTotalPrice( totalPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }



    String getCorrectPrintable( BigDecimal totalPrice )
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