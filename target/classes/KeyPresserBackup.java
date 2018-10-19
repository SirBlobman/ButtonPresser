import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.util.Arrays;

public class KeyPresserBackup implements KeyListener {
	private static ControllerManager CM;
	public static void main(String[] args) {
		KeyPresserBackup kp = new KeyPresserBackup();

		CM = new ControllerManager();
		CM.initSDLGamepad();

		JFrame frame = new JFrame("Key Presser");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(700, 700);
		Image icon = getIconFromJar();
		if(icon != null) frame.setIconImage(icon);

		frame.addKeyListener(kp);
		frame.setVisible(true);

		runAsync(() -> {
		    while(frame.isVisible()) {
		        CM.update();
		        int numControllers = CM.getNumControllers();
		        for(int i = 0; i < numControllers; i++) {
		            ControllerState controllerState = CM.getState(i);
		            if(controllerState.isConnected) {
		                String type = controllerState.controllerType;
		                if(controllerState.a) printController(type, i, "A");
                        if(controllerState.b) printController(type, i, "B");
                        if(controllerState.x) printController(type, i, "X");
                        if(controllerState.y) printController(type, i, "Y");

                        if(controllerState.dpadUp) printController(type, i, "DPad Up");
                        if(controllerState.dpadDown) printController(type, i, "DPad Down");
                        if(controllerState.dpadLeft) printController(type, i, "DPad Left");
                        if(controllerState.dpadRight) printController(type, i, "DPad Right");

                        if(controllerState.back) printController(type, i, "Back (View)");
                        if(controllerState.start) printController(type, i, "Start (Menu)");
                        if(controllerState.guide) printController(type, i, "Guide (Home)");

                        if(controllerState.rightStickClick) printController(type, i, "Click Right Stick");
                        if(controllerState.leftStickClick) printController(type, i, "Click Left Stick");

                        if(controllerState.lb) printController(type, i, "L");
                        if(controllerState.rb) printController(type, i, "R");

                        if(controllerState.leftTrigger > 0) printController(type, i, "ZL (Left Trigger) (" + controllerState.leftTrigger + ")");
                        if(controllerState.rightTrigger > 0) printController(type, i, "ZR (Right Trigger) (" + controllerState.rightTrigger + ")");

                        if(controllerState.rightStickMagnitude > 0) {
                            float angle = controllerState.rightStickAngle;
                            if(angle == 0) printController(type, i, "Right Stick (Right)");
                            if(angle == 90) printController(type, i, "Right Stick (Up)");
                            if(angle == 180) printController(type, i, "Right Stick (Left)");
                            if(angle == 270) printController(type, i, "Right Stick (Down)");
                        }

                        if(controllerState.leftStickMagnitude > 0) {
                            float angle = controllerState.leftStickAngle;
                            if(angle == 0) printController(type, i, "Right Stick (Right)");
                            if(angle == 90) printController(type, i, "Right Stick (Up)");
                            if(angle == 180) printController(type, i, "Right Stick (Left)");
                            if(angle == 270) printController(type, i, "Right Stick (Down)");
                        }
                    }
                }
            }

            CM.quitSDLGamepad();
        });
	}

    private static void print(Object... oo) {
        Arrays.stream(oo).forEach(obj -> {
            String str = obj.toString();
            System.out.println(str);
        });
    }

    private static void printController(String type, int index, String key) {
	    print(type + " " + index + ": " + key);
    }

    private static void runAsync(Runnable run) {
	    new Thread(run).start();
    }

    private static Image getIconFromJar() {
        try {
            Class<KeyPresserBackup> clazz = KeyPresserBackup.class;
            InputStream is = clazz.getResourceAsStream("/icon.png");
            return ImageIO.read(is);
        } catch(Throwable ex) {
            String error = "Failed to load image from jar!";
            System.out.println(error);
            ex.printStackTrace();
            return null;
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyDisplay = KeyEvent.getKeyText(keyCode);

		print("Released Key: '" + keyCode + "' : '" + keyDisplay + "'");
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyDisplay = KeyEvent.getKeyText(keyCode);

		print("Pressed Key: '" + keyCode + "' : '" + keyDisplay + "'");
	}
}