package com.SirBlobman.buttons;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JCreatorUtil {
    public static JLabel createCenteredLabel(String text) {
        return new JLabel(text, SwingConstants.CENTER);
    }
    
    public static JLabel centerLabelLocation(JLabel label, int x, int y) {
        int labelX = (x - (label.getWidth() / 2));
        int labelY = (y - (label.getHeight() / 2));
        label.setLocation(labelX, labelY);
        return label;
    }
}