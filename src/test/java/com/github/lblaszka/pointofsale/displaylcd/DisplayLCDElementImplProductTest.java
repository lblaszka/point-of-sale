package com.github.lblaszka.pointofsale.displaylcd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith( MockitoJUnitRunner.class )
public class DisplayLCDElementImplProductTest
{
    @Test
    public void correctCreate()
    {
        //GET
        String productLabel = "Product Label";
        BigDecimal productPrice = new BigDecimal( 12 );
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( null, productPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( productLabel, null );

        String expectedRender =  getCorrectRender( productLabel, null );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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
        DisplayLCDElementImplProduct displayLCDElementImplProduct = new DisplayLCDElementImplProduct( productLabel, productPrice );

        String expectedRender = getCorrectRender( productLabel, productPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

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