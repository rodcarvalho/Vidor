
package com.pi.vidor.screens;

import java.util.Stack;

/**
 *
 * @author Francisco
 */
public class ScreenManager {
 
    private Stack<AbstractScreen> screens;

    public ScreenManager() {
        this.screens = new Stack<AbstractScreen>();
    }
    
    public void push(AbstractScreen screen) {
        screens.push(screen);
    }
    
    public void pop() {
        screens.pop();
    }
    
    public AbstractScreen set(AbstractScreen screen) {
        screens.push(screen);
        return screens.peek();
    }
    
    public AbstractScreen setback() {
        screens.pop();
        return screens.peek();
    }
    
    public AbstractScreen peek() {
        return screens.peek();
    }
    
    public void dispose() {
        for (AbstractScreen screen : screens)
            screen.dispose();
    }
}
