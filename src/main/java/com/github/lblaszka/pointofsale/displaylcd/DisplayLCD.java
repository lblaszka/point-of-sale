package com.github.lblaszka.pointofsale.displaylcd;

public interface DisplayLCD
{


    /**
     * @param displayLCDElement - call getRendet() to get string and display him on screen.
     */
    void putOnScreen( DisplayLCDElement displayLCDElement );
}
