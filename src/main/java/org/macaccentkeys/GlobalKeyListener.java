/*
    @jgrac96
    from https://github.com/kwhat/jnativehook/blob/2.2/doc/Keyboard.md
*/

package org.macaccentkeys;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: "+NativeKeyEvent.getKeyText(e.getKeyCode()));

        // handle users wanting to escape application
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }
    }
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: "+NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: "+NativeKeyEvent.getKeyText(e.getKeyCode()));
    }
}
