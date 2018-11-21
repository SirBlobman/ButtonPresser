package com.SirBlobman.buttons;

import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerUnpluggedException;

public class ButtonPresser {
    private static ButtonPresserFrame PANEL;
    public static void main(String[] args) {
        PANEL = new ButtonPresserFrame();
        PANEL.setVisible(true);
        checkControllers();
    }
    
    public static void checkControllers() {
        Runnable task = () -> {
            final ControllerManager manager = new ControllerManager();
            manager.initSDLGamepad();
            
            while(PANEL.isVisible()) {
                try {
                    ControllerIndex index = manager.getControllerIndex(0);
                    String name = index.getName();
                    System.out.println(name);
                    PANEL.setControllerName(name);
                } catch(ControllerUnpluggedException ex) {
                    System.out.println("Disconnected Controller: " + 0);
                }
            }
        };
        
        Thread thread = new Thread(task);
        thread.start();
    }
}