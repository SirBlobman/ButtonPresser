package com.SirBlobman.buttons;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonPresserFrame extends JFrame {
    private static final long serialVersionUID = -7405204845124243948L;
    
    private final Container contentPane;
    public ButtonPresserFrame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.contentPane = new ButtonPresserPanel();
        this.contentPane.setLayout(null);
        this.setContentPane(this.contentPane);
        this.pack();
        
        createLabels();
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
        JLabel controllerNameLabel = new JLabel();
        controllerNameLabel.setSize(200, 12);
        controllerNameLabel.setLocation(180, 279);
        controllerNameLabel.setText("No Controller");
        this.labelMap.put("controllerName", (JLabel) this.contentPane.add(controllerNameLabel));
    }
    
    public void setControllerName(String name) {
        JLabel label = labelMap.getOrDefault("controllerName", null);
        if(label != null) {
            label.setText(name);
        }
    }
    
    public static class ButtonPresserPanel extends JPanel {
        private static final long serialVersionUID = -704134000780600570L;
        
        public ButtonPresserPanel() {
            this.setPreferredSize(new Dimension(482, 327));
        }
        
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            
            BufferedImage backgroundImage = readImageFromJar("/controller.png");
            graphics.drawImage(backgroundImage, 0, 0, null);
        }
    }
}