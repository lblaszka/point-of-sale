package com.github.lblaszka.pointofsale.scanner;

import com.github.lblaszka.pointofsale.barcode.BarCode;

import java.util.Optional;

public interface Scanner
{

    /**
     * Scanning bar code.
     *
     * @return  Optional object with scanned barCode or null presence.
     */
    Optional<BarCode> getBarCode();
}