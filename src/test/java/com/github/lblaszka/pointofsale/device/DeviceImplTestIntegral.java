package com.github.lblaszka.pointofsale.device;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainerImpl;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCD;
import com.github.lblaszka.pointofsale.pricecalculator.PriceCalculator;
import com.github.lblaszka.pointofsale.pricecalculator.PriceCalculatorImpl;
import com.github.lblaszka.pointofsale.printer.Printer;
import com.github.lblaszka.pointofsale.printer.PrinterElement;
import com.github.lblaszka.pointofsale.product.*;
import com.github.lblaszka.pointofsale.scanner.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith( MockitoJUnitRunner.class )
public class DeviceImplTestIntegral
{

    @Mock
    private ProductRepository productRepository;
    @Mock
    private Scanner scanner;

    private Printer printer = printerElementList ->
    {
        System.out.println();
        for( PrinterElement printerElement : printerElementList )
        {
            System.out.println("PRINTER: " +printerElement.getPrintable());
        }
    };

    private DisplayLCD displayLCD = displayLCDElement -> System.out.println("LCD: " + displayLCDElement.getRender());

    private Device device;
    private PriceCalculator priceCalculator;
    private ProductService productService;
    private ProductConverter productConverter;

    @Before
    public void setUp()
    {
        priceCalculator = new PriceCalculatorImpl();
        productConverter = new ProductConverterImpl();
        productService = new ProductServiceImpl( productRepository, productConverter );
        device = new DeviceImpl( scanner, displayLCD, printer,productService, priceCalculator );
    }

    @Test
    public void findAllProducts()
    {
        //GET
        //SCANNER:
        List<BarCodeContainerImpl> barCodeContainerImpls = new ArrayList<>( 2 );
        barCodeContainerImpls.add( new BarCodeContainerImpl( "CODE-0" ) );
        barCodeContainerImpls.add( new BarCodeContainerImpl( "CODE-1" ) );
        Mockito.when( scanner.getBarCode() ).thenReturn(
                Optional.ofNullable( barCodeContainerImpls.get( 0 ) )
                ,Optional.ofNullable( barCodeContainerImpls.get( 1 ) )
                ,Optional.ofNullable( new BarCodeContainerImpl( "EXIT" ) )
        );

        //DATA BASE:
        List<Product> productList = new ArrayList<>( 2 );
        productList.add( new Product( 1L, "Product 1", "CODE-0", new BigDecimal( 10.50f ) ) );
        productList.add( new Product( 2L, "Project 2", "CODE-1", new BigDecimal( 12.50f ) ) );
        Mockito.when( productRepository.findByBarCode( "CODE-0" ) ).thenReturn( Optional.ofNullable( productList.get( 0 ) ) );
        Mockito.when( productRepository.findByBarCode( "CODE-1" ) ).thenReturn( Optional.ofNullable( productList.get( 1 ) ) );

        device.scanProducts();
    }

    @Test
    public void scannerReturnEmptyOptional()
    {
        //GET
        //SCANNER:
        List<BarCodeContainerImpl> barCodeContainerImpls = new ArrayList<>( 2 );
        barCodeContainerImpls.add( new BarCodeContainerImpl( "CODE-0" ) );
        barCodeContainerImpls.add( null );
        Mockito.when( scanner.getBarCode() ).thenReturn(
                Optional.ofNullable( barCodeContainerImpls.get( 0 ) )
                ,Optional.ofNullable( barCodeContainerImpls.get( 1 ) )
                ,Optional.ofNullable( new BarCodeContainerImpl( "EXIT" ) )
        );

        //DATA BASE:
        List<Product> productList = new ArrayList<>( 2 );
        productList.add( new Product( 1L, "Product 1", "CODE-0", new BigDecimal( 10.50f ) ) );
        productList.add( new Product( 2L, "Project 2", "CODE-1", new BigDecimal( 12.50f ) ) );
        Mockito.when( productRepository.findByBarCode( "CODE-0" ) ).thenReturn( Optional.ofNullable( productList.get( 0 ) ) );
        Mockito.when( productRepository.findByBarCode( "CODE-1" ) ).thenReturn( Optional.ofNullable( productList.get( 1 ) ) );

        device.scanProducts();
    }

    @Test
    public void codeBarWithoutProductInDB()
    {
        //GET
        //SCANNER:
        List<BarCodeContainerImpl> barCodeContainerImpls = new ArrayList<>( 2 );
        barCodeContainerImpls.add( new BarCodeContainerImpl( "CODE-0" ) );
        barCodeContainerImpls.add( new BarCodeContainerImpl( "CODE-1" ) );
        Mockito.when( scanner.getBarCode() ).thenReturn(
                Optional.ofNullable( barCodeContainerImpls.get( 0 ) )
                ,Optional.ofNullable( barCodeContainerImpls.get( 1 ) )
                ,Optional.ofNullable( new BarCodeContainerImpl( "EXIT" ) )
        );

        //DATA BASE:
        List<Product> productList = new ArrayList<>( 2 );
        productList.add( new Product( 1L, "Product 1", "CODE-0", new BigDecimal( 10.50f ) ) );
        productList.add( null );
        Mockito.when( productRepository.findByBarCode( "CODE-0" ) ).thenReturn( Optional.ofNullable( productList.get( 0 ) ) );
        Mockito.when( productRepository.findByBarCode( "CODE-1" ) ).thenReturn( Optional.ofNullable( productList.get( 1 ) ) );

        device.scanProducts();
    }

    @Test
    public void noProduct()
    {
        //GET
        //SCANNER:
        Mockito.when( scanner.getBarCode() ).thenReturn(Optional.ofNullable(
                new BarCodeContainerImpl( "EXIT" ) )
        );

        device.scanProducts();
    }

}