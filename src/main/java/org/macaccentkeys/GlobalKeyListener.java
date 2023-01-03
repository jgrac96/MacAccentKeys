/*
    @jgrac96
    from https://github.com/kwhat/jnativehook/blob/2.2/doc/Keyboard.md
*/

package org.macaccentkeys;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.Arrays;
public class GlobalKeyListener implements NativeKeyListener {
    final private String[] accentedCharacters = {"A", "E", "I", "O", "U"};
    final private ArrayList accentedKeys = new ArrayList<>(Arrays.asList(accentedCharacters));
    private long[] startPressTime = new long[accentedKeys.size()];
    public void nativeKeyPressed(NativeKeyEvent e) {
        // handle users wanting to escape application
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        }

        // check if key can have accent on
        if (accentedKeys.contains(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
            int referenceIndex = accentedKeys.indexOf(NativeKeyEvent.getKeyText(e.getKeyCode()));
            // check if key has not been pressed before
            if (startPressTime[referenceIndex] == 0) {
                startPressTime[referenceIndex] = System.currentTimeMillis();
            }

        }
    }
    public void nativeKeyReleased(NativeKeyEvent e) {
        // check if key can have accent on
        if (accentedKeys.contains(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
            int referenceIndex = accentedKeys.indexOf(NativeKeyEvent.getKeyText(e.getKeyCode()));
            // check if key has already been pressed and calculate time passed
            if (startPressTime[referenceIndex] != 0) {
                long diff = System.currentTimeMillis() - startPressTime[referenceIndex];
                startPressTime[referenceIndex] = 0; // reset

                if (diff > 200) {
                    // if it has been held for longer compared to normal, then offer popup
                    System.out.println("Options to be selected");
                }
            }

        }
    }
}
