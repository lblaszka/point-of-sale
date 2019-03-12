package com.github.lblaszka.pointofsale.displaylcd;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

@RunWith( MockitoJUnitRunner.class )
public class DisplayLCDElementImplTotalPriceTest
{
    @Test
    public void correctCreate()
    {
        //GET
        BigDecimal totalPrice = new BigDecimal( 12 );
        DisplayLCDElementImplTotalPrice displayLCDElementImplProduct = new DisplayLCDElementImplTotalPrice( totalPrice );

        String expectedRender = getCorrectRender( totalPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

        //THEN
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void createWithoutTotalPrice()
    {
        //GET
        DisplayLCDElementImplTotalPrice displayLCDElemenImplProduct = new DisplayLCDElementImplTotalPrice( null );

        String expectedRender =  getCorrectRender( null );

        //WHEN
        String returnedRender = displayLCDElemenImplProduct.getRender();

        //THEN
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    @Test
    public void roundPriceValue()
    {
        //GET
        BigDecimal totalPrice = new BigDecimal( 124.123523d );
        DisplayLCDElementImplTotalPrice displayLCDElementImplProduct = new DisplayLCDElementImplTotalPrice( totalPrice );

        String expectedRender = getCorrectRender( totalPrice );

        //WHEN
        String returnedRender = displayLCDElementImplProduct.getRender();

        //THEN
        Assert.assertNotNull(returnedRender);
        Assert.assertEquals( expectedRender, returnedRender );
    }

    String getCorrectRender( BigDecimal price )
    {
        if( price != null )
        {
            return String.format( "%-20s : ", "     TOTAL PRICE:" )+price.setScale( 2, RoundingMode.HALF_UP )+" PLN";
        }
        else
        {
            return String.format( "%-20s : ", "     TOTAL PRICE:" )+"N/A";
        }
    }
}