package com.SirBlobman.buttonpresser;

import com.SirBlobman.buttonpresser.shape.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ButtonGUI extends JFrame {
    private static final Image IMAGE_BACKGROUND = getImage("/background.png");
    private static final Image IMAGE_ICON = getImage("/icon.png");

    private final List<Shape> shapesToDraw = new ArrayList<>();
    public ButtonGUI() {
        setTitle("Button Presser");
        setIconImage(IMAGE_ICON);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);

        setVisible(true);
        addKeyListener(new KeyListenerBP(this));
        repaint();
    }

    private static Image getImage(String fileNameInJar) {
        try {
            Class<?> class_ButtonPresser = ButtonPresser.class;
            URL fileURL = class_ButtonPresser.getResource(fileNameInJar);
            return ImageIO.read(fileURL);
        } catch(Throwable ex) {
            String error = "Failed to get image named '" + fileNameInJar + "' from jar:";
            System.out.println(error);
            ex.printStackTrace();
            return new BufferedImage(0, 0, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public void addShape(Shape shape) {
        if(!this.shapesToDraw.contains(shape)) this.shapesToDraw.add(shape);
    }

    public void removeShape(Shape shape) {
        this.shapesToDraw.remove(shape);
    }


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(IMAGE_BACKGROUND, 0, 0, null);

        graphics.setPaintMode();
        List<Shape> copyList = new ArrayList(this.shapesToDraw);
        copyList.forEach(shape -> shape.draw(graphics));
    }
}