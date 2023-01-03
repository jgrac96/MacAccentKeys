/*
    @jgrac96

    from https://github.com/kwhat/jnativehook/blob/2.2/doc/Keyboard.md

    Simple Application to emulate how Mac Key System works for accents
 */
package org.macaccentkeys;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class Main{

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.out.println("There was a problem registering the native hook.");
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
    }
}