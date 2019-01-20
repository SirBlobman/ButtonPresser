package com.SirBlobman.buttons;

public class ButtonPresser {
    public static void main(String[] args) {
        ButtonPresserFrame panel = new ButtonPresserFrame();        
        panel.setVisible(true);
        checkControllers(panel);
    }
    
    public static void checkControllers(ButtonPresserFrame panel) {
        ControllerTask task = new ControllerTask(panel);
        Thread thread = new Thread(task);
        thread.start();
    }
}