package com.github.lblaszka.pointofsale.pricecalculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith( MockitoJUnitRunner.class )
public class PriceCalculatorImplTest
{
    private List<BigDecimal> priceList;

    @Before
    public void setUp()
    {
        priceList = new LinkedList<>( );
        priceList.add( new BigDecimal( 12 ) );
        priceList.add( new BigDecimal( 12.12f ) );
        priceList.add( new BigDecimal( 12.214f ) );
        priceList.add( new BigDecimal( 213 ) );
        priceList.add( new BigDecimal( 112 ) );
        priceList.add( new BigDecimal( 52.234d ) );
    }

    @Test
    public void getTotalPriceFromListPrice()
    {
        //GET
        BigDecimal expectedTotalPrice = new BigDecimal( 0 );
        for( BigDecimal price : priceList )
        {
            expectedTotalPrice = expectedTotalPrice.add( price );
        }

        //WHEN
        BigDecimal totalPrice = new PriceCalculatorImpl().getTotalPrice( priceList );

        //THEN
        Assert.assertNotNull( totalPrice );
        Assert.assertThat( totalPrice, is( expectedTotalPrice ) );
    }


    @Test
    public void getTotalPriceWithoutListPrice()
    {
        //GET
        BigDecimal expectedTotalPrice = new BigDecimal( 0 );

        //WHEN
        BigDecimal totalPrice = new PriceCalculatorImpl().getTotalPrice( null );

        //THEN
        Assert.assertNotNull( totalPrice );
        Assert.assertThat( totalPrice, is( expectedTotalPrice ) );
    }


    @Test
    public void getTotalPriceWithEmptyListPrice()
    {
        //GET
        priceList = new ArrayList<>(  );
        BigDecimal expectedTotalPrice = new BigDecimal( 0 );

        //WHEN
        BigDecimal totalPrice = new PriceCalculatorImpl().getTotalPrice( priceList );

        //THEN
        Assert.assertNotNull( totalPrice );
        Assert.assertThat( totalPrice, is( expectedTotalPrice ) );

    }

    @Test
    public void getTotalPriceWithListPriceWithEmptyFields()
    {
        //GET
        BigDecimal expectedTotalPrice = new BigDecimal( 0 );
        for( BigDecimal price : priceList )
        {
            if( price == null )
                continue;

            expectedTotalPrice = expectedTotalPrice.add( price );
        }

        //WHEN
        BigDecimal totalPrice = new PriceCalculatorImpl().getTotalPrice( priceList );

        //THEN
        Assert.assertNotNull( totalPrice );
        Assert.assertThat( totalPrice, is( expectedTotalPrice ) );
    }
}