package com.SirBlobman.buttonpresser;

import com.SirBlobman.buttonpresser.shape.Shape;
import com.SirBlobman.buttonpresser.shape.ShapeFilledCircle;
import com.SirBlobman.buttonpresser.shape.ShapeFilledRectangle;
import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

public class KeyListenerBP implements KeyListener {
    private static final Map<Integer, Shape> KEY_CACHE = new HashMap<>();
    private static final Map<ControllerButton, Shape> BUTTON_CACHE = new HashMap<>();
    private static final List<Integer> ACTIVE_KEYS = new ArrayList<>();
    private static final List<ControllerButton> ACTIVE_BUTTONS = new ArrayList<>();
    private final ButtonGUI buttonGUI;
    public KeyListenerBP(ButtonGUI buttonGUI) {
        this.buttonGUI = buttonGUI;
        runAsync(() -> {
            ControllerManager controllerManager = new ControllerManager();
            controllerManager.initSDLGamepad();
            while(buttonGUI.isVisible()) {
                try {
                    controllerManager.update();
                    int connectedControllers = controllerManager.getNumControllers();
                    for(int i = 0; i < connectedControllers; i++) {
                        ControllerState cs = controllerManager.getState(i);
                        if(cs.isConnected) {
                            String type = cs.controllerType;
                            String lowerType = type.toLowerCase();
                            if(lowerType.contains("xinput") || lowerType.contains("xbox")) {
                                ControllerIndex ci = controllerManager.getControllerIndex(i);
                                for(ControllerButton cb : ControllerButton.values()) {
                                    if(ci.isButtonPressed(cb)) {
                                        if(!ACTIVE_BUTTONS.contains(cb)) {
                                            Shape shape = getShape(cb);
                                            if(shape != null) {
                                                buttonGUI.addShape(shape);
                                                buttonGUI.repaint();
                                            }
                                            ACTIVE_BUTTONS.add(cb);
                                        }
                                    } else {
                                        if(ACTIVE_BUTTONS.contains(cb)) {
                                            Shape shape = getShape(cb);
                                            if(shape != null) {
                                                buttonGUI.removeShape(shape);
                                                buttonGUI.repaint();
                                            }
                                            ACTIVE_BUTTONS.remove(cb);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch(Throwable ex) {
                    String error = "An error occured with controllers:";
                    System.out.println(error);
                    ex.printStackTrace();
                }
            }
            controllerManager.quitSDLGamepad();
            System.exit(0);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(!ACTIVE_KEYS.contains(keyCode)) {
            Shape shape = getShape(keyCode);
            if(shape != null) {
                buttonGUI.addShape(shape);
                ACTIVE_KEYS.add(keyCode);
                buttonGUI.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(ACTIVE_KEYS.contains(keyCode)) {
            Shape shape = getShape(keyCode);
            if(shape != null) {
                buttonGUI.removeShape(shape);
                ACTIVE_KEYS.remove((Object) keyCode);
                buttonGUI.repaint();
            }
        }
    }
    
    private void runAsync(Runnable run) {
        new Thread(run).start();
    }

    private Shape getShape(int keyCode) {
        if(KEY_CACHE.containsKey(keyCode)) return KEY_CACHE.get(keyCode);

        Shape shape = null;
        Color semiTransparentRed = new Color(255, 0, 0, 128);
        switch(keyCode) {
            case KeyEvent.VK_ESCAPE: // Esc
                shape = new ShapeFilledRectangle(semiTransparentRed,84, 443, 29, 18);
                break;
            case KeyEvent.VK_F1: // F1
                shape = new ShapeFilledRectangle(semiTransparentRed,118, 443, 29, 18);
                break;
            case KeyEvent.VK_F2:
                shape = new ShapeFilledRectangle(semiTransparentRed,152, 443, 29, 18);
                break;
            case KeyEvent.VK_F3:
                shape = new ShapeFilledRectangle(semiTransparentRed,186, 443, 29, 18);
                break;
            case KeyEvent.VK_F4:
                shape = new ShapeFilledRectangle(semiTransparentRed,220, 443, 29, 18);
                break;
            case KeyEvent.VK_F5:
                shape = new ShapeFilledRectangle(semiTransparentRed,254, 443, 29, 18);
                break;
            case KeyEvent.VK_F6:
                shape = new ShapeFilledRectangle(semiTransparentRed,288, 443, 29, 18);
                break;
            case KeyEvent.VK_F7:
                shape = new ShapeFilledRectangle(semiTransparentRed,322, 443, 29, 18);
                break;
            case KeyEvent.VK_F8:
                shape = new ShapeFilledRectangle(semiTransparentRed,356, 443, 29, 18);
                break;
            case KeyEvent.VK_F9:
                shape = new ShapeFilledRectangle(semiTransparentRed,390, 443, 29, 18);
                break;
            case KeyEvent.VK_F10:
                shape = new ShapeFilledRectangle(semiTransparentRed,424, 443, 29, 18);
                break;
            case KeyEvent.VK_F11:
                shape = new ShapeFilledRectangle(semiTransparentRed,458, 443, 29, 18);
                break;
            case KeyEvent.VK_F12:
                shape = new ShapeFilledRectangle(semiTransparentRed,492, 443, 29, 18);
                break;
            case KeyEvent.VK_DELETE:
                shape = new ShapeFilledRectangle(semiTransparentRed,527, 443, 29, 18);
                break;
            case KeyEvent.VK_END:
                shape = new ShapeFilledRectangle(semiTransparentRed,561, 443, 29,18);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                shape = new ShapeFilledRectangle(semiTransparentRed,595, 443, 29, 18);
                break;
            default: break;
        }
        
        if(shape != null) KEY_CACHE.put(keyCode, shape);
        return shape;
    }

    public Shape getShape(ControllerButton button) {
        if(BUTTON_CACHE.containsKey(button)) return BUTTON_CACHE.get(button);

        Shape shape = null;
        Color semiTransparentRed = new Color(255, 0, 0, 128);
        Color semiTransparentBlue = new Color(0, 0, 255, 128);
        Color semiTransparentGreen = new Color(0, 255, 0, 128);
        switch(button) {
            case A:
                shape = new ShapeFilledCircle(semiTransparentBlue,456, 149, (34/2));
                break;
            case X:
                shape = new ShapeFilledCircle(semiTransparentBlue, 423, 117, (34/2));
                break;
            case Y:
                shape = new ShapeFilledCircle(semiTransparentBlue, 457, 87, (34/2));
                break;
            case B:
                shape = new ShapeFilledCircle(semiTransparentBlue, 490, 117, (34/2));
                break;
            case GUIDE:
                shape = new ShapeFilledCircle(semiTransparentGreen, 330, 61, (44/2));
                break;
            case BACK:
                shape = new ShapeFilledCircle(semiTransparentGreen, 306, 124, (20/2));
                break;
            case START:
                shape = new ShapeFilledCircle(semiTransparentGreen, 376, 124, (20/2));
                break;
            default: break;
        }

        if(shape != null) BUTTON_CACHE.put(button, shape);
        return shape;
    }
}
