package com.github.lblaszka.pointofsale.device;

import com.github.lblaszka.pointofsale.displaylcd.DisplayLCD;
import com.github.lblaszka.pointofsale.pricecalculator.PriceCalculator;
import com.github.lblaszka.pointofsale.printer.Printer;
import com.github.lblaszka.pointofsale.product.ProductService;
import com.github.lblaszka.pointofsale.scanner.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith( MockitoJUnitRunner.class )
public class DeviceImplTest
{
    @Mock
    PriceCalculator priceCalculator;
    @Mock
    ProductService productService;
    @Mock
    Printer printer;
    @Mock
    DisplayLCD displayLCD;
    @Mock
    Scanner scanner;

    @Test
    public void breakOnExitCode()
    {

    }

    @Test
    public void notPresenceBarCodeContainer()
    {

    }

    @Test
    public void notPresenceProductDTO()
    {

    }

    @Test
    public void communicationsOfErrors()
    {

    }

    @Test
    public void listSavedProducts()
    {

    }

    @Test
    public void totalPrice()
    {

    }



}