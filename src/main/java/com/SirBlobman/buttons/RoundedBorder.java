package com.SirBlobman.buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
    private final int radius;
    private final Color color;
    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
    
    public Insets getBorderInsets(Component component) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }
    
    public boolean isBorderOpaque() {
        return true;
    }
    
    public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
        graphics.setColor(color);
        graphics.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}