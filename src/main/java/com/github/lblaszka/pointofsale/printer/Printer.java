package com.github.lblaszka.pointofsale.printer;

import java.util.List;

public interface Printer
{

    /**
     * @param printerElementList - list of elements who should be printed.
     */
    void printElements( List<PrinterElement> printerElementList );
}
