package infra;

import java.util.HashSet;
import java.util.Set;

/**
 * Input class.
 * 
 * @author Leonardo Ono (ono.leo80@gmail.com)
 */
public class Input {
    
    public static final Set<Integer> KEYS_PRESSED = new HashSet<>();
    public static final Set<Integer> KEYS_PRESSED_CONSUMED = new HashSet<>();

    public static final Set<Integer> KEYS_DOUBLE_PRESSED_SET= new HashSet<>();

    public static synchronized boolean isKeyPressed(int keyCode) {
        return KEYS_PRESSED.contains(keyCode);
    }

    public static synchronized boolean isKeyDoublePressed(int keyCode) {
        return KEYS_DOUBLE_PRESSED_SET.remove(keyCode) && KEYS_PRESSED.contains(keyCode);
    }

    public static synchronized boolean isKeyJustPressed(int keyCode) {
        if (!KEYS_PRESSED_CONSUMED.contains(keyCode)
                && KEYS_PRESSED.contains(keyCode)) {
            
            KEYS_PRESSED_CONSUMED.add(keyCode);
            return true;
        }
        return false;
    }
}
