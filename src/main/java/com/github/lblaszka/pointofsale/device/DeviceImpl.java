package com.github.lblaszka.pointofsale.device;

import com.github.lblaszka.pointofsale.barcode.BarCodeContainer;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCD;
import com.github.lblaszka.pointofsale.displaylcd.DisplayLCDElementImplProduct;
import com.github.lblaszka.pointofsale.printer.Printer;
import com.github.lblaszka.pointofsale.product.ProductDTO;
import com.github.lblaszka.pointofsale.product.ProductService;
import com.github.lblaszka.pointofsale.scanner.Scanner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DeviceImpl implements Device
{
    private Scanner scanner;
    private DisplayLCD displayLCD;
    private Printer printer;
    private ProductService productService;

    private List<ProductDTO> productList = new LinkedList<>(  );


    public DeviceImpl( Scanner scanner, DisplayLCD displayLCD, Printer printer, ProductService productService )
    {
        this.scanner = scanner;
        this.displayLCD = displayLCD;
        this.printer = printer;
        this.productService = productService;
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

    }

    private void putOnDisplayInvalidBarCode()
    {
        displayLCD.putOnScreen( () -> { return "Product not found";} );
    }

    private void putOnDisplayProductNotFound()
    {
        displayLCD.putOnScreen( () -> { return "Invalid bar-code";} );
    }


}
