package com.github.lblaszka.pointofsale.displaylcd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.Assert.*;

@RunWith( MockitoJUnitRunner.class )
public class DisplayLCDElemenImplProductTest
{
    @Test
    public void correctCreate()
    {
        //GET
        String productLabel = "Product Label";
        BigDecimal productPrice = new BigDecimal( 12 );
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void createWithoutLabel()
    {
        //GET
        BigDecimal productPrice = new BigDecimal( 12 );
        String expectedRender = getCorrectRender( null, productPrice );
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( null, productPrice );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender , returnedRender );
    }

    @Test
    public void createWithoutPrice()
    {
        //GET
        String productLabel = "Product Label";
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( productLabel, null );

        String expectedRender =  getCorrectRender( productLabel, null );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void shorteningTheLongLabel()
    {
        //GET
        String productLabel = "This is too long label to full showing";
        BigDecimal productPrice = new BigDecimal( 23.23f );
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void roundPriceValue()
    {
        //GET
        String productLabel = "Product Label";
        BigDecimal productPrice = new BigDecimal( 12.214324235d );
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void createWithLabelWithoutChars()
    {
        //GET
        String productLabel = "";
        BigDecimal productPrice = new BigDecimal( 12 );
        DisplayLCDElemenImplProduct displayLCDElemenImplProduct = new DisplayLCDElemenImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        System.out.println(expectedRender);
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    String getCorrectRender( String label, BigDecimal price )
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