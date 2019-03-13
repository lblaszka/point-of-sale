package com.github.lblaszka.pointofsale.device;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCD;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCDElementImplProduct;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCDElementImplTotalPrice;
import com.github.lblaszka.pointofsale.pricecalculator.PriceCalculator;
import com.github.lblaszka.pointofsale.printer.Printer;
import com.github.lblaszka.pointofsale.printer.PrinterElement;
import com.github.lblaszka.pointofsale.printer.PrinterElementImplProduct;
import com.github.lblaszka.pointofsale.printer.PrinterElementImplTotalPrice;
import com.github.lblaszka.pointofsale.product.Product;
import com.github.lblaszka.pointofsale.product.ProductDTO;
import com.github.lblaszka.pointofsale.product.ProductService;
import com.github.lblaszka.pointofsale.scanner.Scanner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DeviceImpl implements Device
{
    private Scanner scanner;
    private DisplayLCD displayLCD;
    private Printer printer;
    private ProductService productService;
    private PriceCalculator priceCalculator;

    private List<ProductDTO> productList = new LinkedList<>(  );


    public DeviceImpl( Scanner scanner, DisplayLCD displayLCD, Printer printer, ProductService productService, PriceCalculator priceCalculator, List<ProductDTO> productList )
    {
        this.scanner = scanner;
        this.displayLCD = displayLCD;
        this.printer = printer;
        this.productService = productService;
        this.priceCalculator = priceCalculator;
        this.productList = productList;
    }


    @Override
    public void scanProducts()
    {
        while( true )
        {
            Optional<BarCodeContainer> barCodeContainerOptional = scanner.getBarCode();

            if( barCodeContainerOptional.isPresent() )
            {
                if( barCodeContainerOptional.get().equals( "EXIT" ) )
                    break;

                Optional<ProductDTO> productOptional = productService.findByBarCode( barCodeContainerOptional.get() );

                if( productOptional.isPresent() )
                {
                    productList.add( productOptional.get() );
                    displayLCD.putOnScreen( new DisplayLCDElementImplProduct(
                            productOptional.get().getLabel()
                            ,productOptional.get().getPrice() )
                    );
                }
                else
                    putOnDisplayProductNotFound();
            }
            else
                putOnDisplayInvalidBarCode();
        }

        BigDecimal totalPrice = getTotalPrice( productList );
        displayLCD.putOnScreen( new DisplayLCDElementImplTotalPrice( totalPrice ) );
        printSalesReceipt( productList, totalPrice );

    }

    private void putOnDisplayInvalidBarCode()
    {
        displayLCD.putOnScreen( () -> { return "Product not found";} );
    }

    private void putOnDisplayProductNotFound()
    {
        displayLCD.putOnScreen( () -> { return "Invalid bar-code";} );
    }

    private BigDecimal getTotalPrice( List<ProductDTO> _productList )
    {
        List<BigDecimal> priceList = new ArrayList<>( _productList.size() );
        for( ProductDTO product : _productList )
        {
            priceList.add( product.getPrice() );
        }

        return priceCalculator.getTotalPrice( priceList );
    }

    private void printSalesReceipt( List<ProductDTO> _productList, BigDecimal _totalPrice )
    {
        List<PrinterElement> printerElements = new ArrayList<>( _productList.size() + 1);
        for( ProductDTO product : _productList )
        {
            printerElements.add(
                new PrinterElementImplProduct(
                        product.getLabel(),
                        product.getPrice()
                )
            );
        }
        printerElements.add( new PrinterElementImplTotalPrice( _totalPrice ) );

        printer.printElements( printerElements );
    }


}
