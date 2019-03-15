package com.github.lblaszka.pointofsale.device;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCD;
import com.github.lblaszka.pointofsale.pricecalculator.PriceCalculator;
import com.github.lblaszka.pointofsale.printer.Printer;
import com.github.lblaszka.pointofsale.printer.PrinterElement;
import com.github.lblaszka.pointofsale.product.ProductDTO;
import com.github.lblaszka.pointofsale.product.ProductService;
import com.github.lblaszka.pointofsale.scanner.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RunWith( MockitoJUnitRunner.class )
public class DeviceImplTest
{
    @Mock
    private PriceCalculator priceCalculator;
    @Mock
    private ProductService productService;
    @Mock
    private Printer printer;
    @Mock
    private DisplayLCD displayLCD;
    @Mock
    private Scanner scanner;

    private Device device;

    @Before
    public void setUp()
    {
        device = new DeviceImpl( scanner, displayLCD, printer, productService, priceCalculator );
    }

    @Test
    public void breakOnExitCode()
    {
        Mockito.when( scanner.getBarCode() )
                .thenReturn(
                        Optional.ofNullable( new BarCodeContainer( new String( "EXIT") ) )
                );

        device.scanProducts();

        Mockito.verify( scanner, Mockito.times( 1 ) ).getBarCode();
        Mockito.verify( productService, Mockito.times( 0 ) ).findByBarCode( Matchers.anyObject() );
        Mockito.verify( displayLCD, Mockito.times( 1 ) ).putOnScreen( Matchers.anyObject() );
        Mockito.verify( priceCalculator, Mockito.times( 1 ) ).getTotalPrice( Matchers.anyObject() );
        Mockito.verify( printer, Mockito.times( 1 ) ).printElements( Matchers.anyObject() );

    }

    @Test
    public void notPresenceBarCodeContainer()
    {
        Mockito.when( scanner.getBarCode() )
                .thenReturn(
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("EXIT") ) )
                );

        device.scanProducts();

        Mockito.verify( scanner, Mockito.times( 2 ) ).getBarCode();
        Mockito.verify( productService, Mockito.times( 0 ) ).findByBarCode( Matchers.anyObject() );
        Mockito.verify( displayLCD, Mockito.times( 2 ) ).putOnScreen( Matchers.anyObject() );
        Mockito.verify( priceCalculator, Mockito.times( 1 ) ).getTotalPrice( Matchers.anyObject() );
        Mockito.verify( printer, Mockito.times( 1 ) ).printElements( Matchers.anyObject() );
    }

    @Test
    public void notPresenceProductDTO()
    {
        Mockito.when( scanner.getBarCode() )
                .thenReturn(
                        Optional.of( new BarCodeContainer( null )),
                        Optional.of( new BarCodeContainer( new String("EXIT") ) )
                );
        Mockito.when( productService.findByBarCode( Matchers.anyObject() )).thenReturn( Optional.empty() );

        device.scanProducts();

        Mockito.verify( scanner, Mockito.times( 2 ) ).getBarCode();
        Mockito.verify( productService, Mockito.times( 1 ) ).findByBarCode( Matchers.anyObject() );
        Mockito.verify( displayLCD, Mockito.times( 2 ) ).putOnScreen( Matchers.anyObject() );
        Mockito.verify( priceCalculator, Mockito.times( 1 ) ).getTotalPrice( Matchers.anyObject() );
        Mockito.verify( printer, Mockito.times( 1 ) ).printElements( Matchers.anyObject() );

    }

    @Test
    public void listSavedProducts()
    {


        Mockito.when( scanner.getBarCode() )
                .thenReturn(
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("1") )),
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("2") )),
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("3") )),
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("4") )),
                        Optional.empty(),
                        Optional.of( new BarCodeContainer( new String("EXIT") ) )
                );

        Mockito.when( productService.findByBarCode( Matchers.anyObject() ) )
                .thenReturn(
                    Optional.empty(),
                    Optional.of( new ProductDTO( "1", new BarCodeContainer( null ), null ) ),
                    Optional.empty(),
                    Optional.of( new ProductDTO( "2", new BarCodeContainer( null ), null ) )
                );


        device.scanProducts();


        Mockito.verify( scanner, Mockito.times( 10 ) ).getBarCode();
        Mockito.verify( productService, Mockito.times( 4 ) ).findByBarCode( Matchers.anyObject() );
        Mockito.verify( displayLCD, Mockito.times( 10 ) ).putOnScreen( Matchers.anyObject() );
        Mockito.verify( priceCalculator, Mockito.times( 1 ) ).getTotalPrice( Matchers.anyObject() );
        Mockito.verify( printer, Mockito.times( 1 ) ).printElements( Matchers.anyObject() );

        ArgumentCaptor<List> printerElementListCaptor = ArgumentCaptor.forClass( List.class );
        Mockito.verify( printer ).printElements( printerElementListCaptor.capture());
        Assert.assertEquals( 3, printerElementListCaptor.getValue().size());
    }



}