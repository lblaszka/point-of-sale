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
public class PrinterElementImplProductTest
{
    @Test
    public void getPrintableWithCorrectsValues()
    {
        //GET
        String productLabel = "Correct label";
        BigDecimal productPrice = new BigDecimal( 21f );
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );

    }

    @Test
    public void getPrintableWithoutLabel()
    {
        //GET
        String productLabel = null;
        BigDecimal productPrice = new BigDecimal( 21f );
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPrintableWithLabelWithoutChars()
    {
        //GET
        String productLabel = "";
        BigDecimal productPrice = new BigDecimal( 21f );
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPrintableWithTooLongLabel()
    {
        //GET
        String productLabel = "Too long label to print all chars";
        BigDecimal productPrice = new BigDecimal( 21f );
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPrintableWithoutPrice()
    {
        //GET
        String productLabel = "Correct label";
        BigDecimal productPrice = null;
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    @Test
    public void getPrintableWithRangePrice()
    {
        //GET
        String productLabel = "Correct label";
        BigDecimal productPrice = new BigDecimal( 21.24534f );
        String expectedPrintable = getCorrectPrintable( productLabel, productPrice );


        //WHEN
        String printable = new PrinterElementImplProduct( productLabel, productPrice ).getPrintable();

        //THEN
        System.out.println( expectedPrintable );
        Assert.assertNotNull( printable );
        Assert.assertThat( printable, is(expectedPrintable) );
    }

    String getCorrectPrintable( String label, BigDecimal price )
    {
        if( label != null && label.length() != 0 )
        {
            if( label.length() > 20 )
            {
                label = new String( label.substring( 0,17 )  +"..." );
            }
        }
        else
        {
            label = "*NO LABEL*";
        }

        if( price != null )
        {
            return String.format( "%-20s : ", label )+price.setScale( 2, RoundingMode.HALF_UP )+" PLN";
        }
        else
        {
            return String.format( "%-20s : ", label )+"N/A";
        }
    }

}