package com.SirBlobman.buttons;

import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerUnpluggedException;

public class ControllerTask implements Runnable {
    private final ButtonPresserFrame frame;
    private static ControllerManager manager = null;
    public ControllerTask(ButtonPresserFrame frame) {
        this.frame = frame;
        if(manager == null) {
            manager = new ControllerManager();
            manager.initSDLGamepad();
        }
    }
    
    @Override
    public void run() {
        while(this.frame.isVisible()) {
            try {
                ControllerIndex index = manager.getControllerIndex(0);
                if(index.isConnected()) {
                    String name = index.getName();
                    this.frame.setControllerName(name);
                    
                    float rightX = index.getAxisState(ControllerAxis.RIGHTX);
                    float rightY = index.getAxisState(ControllerAxis.RIGHTY);
                    this.frame.setRightStickX(rightX * 100.0F);
                    this.frame.setRightStickY(rightY * 100.0F);
                    
                    float leftX = index.getAxisState(ControllerAxis.LEFTX);
                    float leftY = index.getAxisState(ControllerAxis.LEFTY);
                    this.frame.setLeftStickX(leftX * 100.0F);
                    this.frame.setLeftStickY(leftY * 100.0F);
                    
                    float leftTrigger = index.getAxisState(ControllerAxis.TRIGGERLEFT);
                    float rightTrigger = index.getAxisState(ControllerAxis.TRIGGERRIGHT);
                    this.frame.setLeftTrigger(leftTrigger * 100.0F);
                    this.frame.setRightTrigger(rightTrigger * 100.0F);
                    
                    for(ControllerButton button : ControllerButton.values()) {
                        boolean pressed = index.isButtonPressed(button);
                        this.frame.setButtonPressed(button, pressed);
                    }
                }
            } catch(ControllerUnpluggedException ex) {
                System.out.println("Disconnected Controller: " + 0);
            }
        }
    }
}