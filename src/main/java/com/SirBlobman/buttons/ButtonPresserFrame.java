package com.SirBlobman.buttons;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.studiohartman.jamepad.ControllerButton;

public class ButtonPresserFrame extends JFrame {
    private static final long serialVersionUID = -7405204845124243948L;
    private static final DecimalFormat AXIS_DEGREES = new DecimalFormat("0.00");
    
    private final Container contentPane;
    public ButtonPresserFrame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BufferedImage panelIcon = readImageFromJar("/icon.png");
        this.setIconImage(panelIcon);
        
        this.contentPane = new ButtonPresserPanel();
        this.contentPane.setLayout(null);
        this.setContentPane(this.contentPane);
        this.pack();
        
        createLabels();
        createButtons();
    }
    
    private static BufferedImage readImageFromJar(String pathToImage) {
        try {
            Class<?> mainClass = ButtonPresser.class;
            InputStream is = mainClass.getResourceAsStream(pathToImage);
            return ImageIO.read(is);
        } catch(Throwable ex) {
            ex.printStackTrace();
            return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
        }
    }
    
    private final Map<String, JLabel> labelMap = new HashMap<>();
    private void createLabels() {
        labelMap.clear();
        
        createAndSaveLabel("controllerName", 712, 949, "No Controller", false);
        
        createAndSaveLabel("leftStickX", 357, 410, "Left Stick X: 0.00%", false);
        createAndSaveLabel("leftStickY", 357, 430, "Left Stick Y: 0.00%", false);
        
        createAndSaveLabel("rightStickX", 903, 630, "Right Stick X: 0.00%", false);
        createAndSaveLabel("rightStickY", 903, 650, "Right Stick Y: 0.00%", false);

        createAndSaveLabel("leftTrigger", 117, 40, "Left Trigger: 0.00%", false);
        createAndSaveLabel("rightTrigger", 1308, 40, "Right Trigger: 0.00%", false);
    }
    
    private void createAndSaveLabel(String labelId, int x, int y, String text, boolean addBorder) {
        String html = "<html>" + text + "</html>";
        JLabel label = createLabel(x, y, html, addBorder);
        this.contentPane.add(label);
        this.labelMap.put(labelId, label);
    }
    
    private JLabel createLabel(int x, int y, String text, boolean border) {
        int labelHeight = getTextHeight();
        int labelWidth = getTextWidth(text);
        
        JLabel label = JCreatorUtil.createCenteredLabel(text);
        label.setSize(labelWidth, labelHeight);
        label = JCreatorUtil.centerLabelLocation(label, x, y);
        if(border) {
            Color blackColor = new Color(0.0F, 0.0F, 0.0F, 1.0F);
            Border blackBorder = new RoundedBorder(10, blackColor);
            label.setBorder(blackBorder);
        }
        
        return label;
    }
    
    private int getTextHeight() {
        Graphics graphics = getGraphics();
        FontMetrics fontMetrics = graphics.getFontMetrics();
        return fontMetrics.getHeight() + 10;
    }
    
    private int getTextWidth(String text) {
        Graphics graphics = getGraphics();
        FontMetrics fontMetrics = graphics.getFontMetrics();
        return fontMetrics.stringWidth(text) + 50;
    }
    
    public void setLabelText(String labelId, String text) {
        JLabel label = this.labelMap.getOrDefault(labelId, null);
        if(label != null) {
            String html = "<html>" + text + "<html>";
            label.setText(html);
        }
    }
    
    public void setControllerName(String name) {
        setLabelText("controllerName", name);
    }
    
    public void setLeftStickX(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Left Stick X: %s%%", textDegrees);
        setLabelText("leftStickX", format);
    }
    
    public void setLeftStickY(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Left Stick Y: %s%%", textDegrees);
        setLabelText("leftStickY", format);
    }
    
    public void setRightStickX(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Right Stick X: %s%%", textDegrees);
        setLabelText("rightStickX", format);
    }
    
    public void setRightStickY(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Right Stick Y: %s%%", textDegrees);
        setLabelText("rightStickY", format);
    }
    
    public void setLeftTrigger(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Left Trigger: %s%%", textDegrees);
        setLabelText("leftTrigger", format);
    }
    
    public void setRightTrigger(double degrees) {
        if(degrees == -0) degrees = 0;
        String textDegrees = AXIS_DEGREES.format(degrees);
        
        String format = String.format("Right Trigger: %s%%", textDegrees);
        setLabelText("rightTrigger", format);
    }
    
    private final Map<ControllerButton, JButton> buttonMap = new EnumMap<>(ControllerButton.class);
    private void createButtons() {
        buttonMap.clear();
        
        createAndSaveButton(646, 68, 134, 134, ControllerButton.GUIDE);
        createAndSaveButton(581, 260, 60, 60, ControllerButton.BACK);
        createAndSaveButton(786, 257, 60, 60, ControllerButton.START);

        createAndSaveButton(925, 237, 111, 111, ControllerButton.X);
        createAndSaveButton(1027, 144, 103, 103, ControllerButton.Y);
        createAndSaveButton(1127, 237, 111, 111, ControllerButton.B);
        createAndSaveButton(1027, 333, 103, 103, ControllerButton.A);

        createAndSaveButton(406, 9, 50, 50, ControllerButton.LEFTBUMPER);
        createAndSaveButton(975, 9, 50, 50, ControllerButton.RIGHTBUMPER);

        createAndSaveButton(310, 240, 109, 109, ControllerButton.LEFTSTICK);
        createAndSaveButton(850, 460, 109, 109, ControllerButton.RIGHTSTICK);

        createAndSaveButton(494, 416, 74, 74, ControllerButton.DPAD_UP);
        createAndSaveButton(425, 482, 74, 74, ControllerButton.DPAD_LEFT);
        createAndSaveButton(492, 547, 74, 74, ControllerButton.DPAD_DOWN);
        createAndSaveButton(563, 484, 74, 74, ControllerButton.DPAD_RIGHT);
    }
    
    private void createAndSaveButton(int x, int y, int width, int height, ControllerButton controllerButton) {
        JButton button = createButton(x, y, width, height);
        buttonMap.put(controllerButton, button);
        this.contentPane.add(button);
    }
    
    private JButton createButton(int x, int y, int width, int height) {
        JButton button = new RoundedButton();
        button.setLocation(x, y);
        button.setSize(width, height);
        
        Color transparent = new Color(0, 0, 0, 0);
        button.setBackground(transparent);
        
        return button;
    }
    
    public void setButtonPressed(ControllerButton button, boolean pressed) {
        JButton jbutton = buttonMap.getOrDefault(button, null);
        if(jbutton != null) {
            Color color = (pressed ? new Color(255, 0, 0, 255) : new Color(0, 0, 0, 0));
            jbutton.setBackground(color);
        }
    }
    
    public static class ButtonPresserPanel extends JPanel {
        private static final long serialVersionUID = -704134000780600570L;
        
        public ButtonPresserPanel() {
            this.setPreferredSize(new Dimension(1425, 959));
        }
        
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            
            BufferedImage backgroundImage = readImageFromJar("/XBox Controller.png");
            graphics.drawImage(backgroundImage, 0, 0, null);
        }
    }
}