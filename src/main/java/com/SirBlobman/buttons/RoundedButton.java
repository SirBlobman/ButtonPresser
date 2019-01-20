package com.SirBlobman.buttons;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

public class RoundedButton extends JButton {
    private static final long serialVersionUID = 5011150280448877377L;
    
    public RoundedButton() {
        super();
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        
        setContentAreaFilled(false);
    }
    
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.setColor(getBackground());
        graphics.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(graphics);
    }
    
    @Override
    public void paintBorder(Graphics graphics) {
        //graphics.setColor(getForeground());
        //graphics.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
}