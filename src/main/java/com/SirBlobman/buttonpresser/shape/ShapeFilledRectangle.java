package com.SirBlobman.buttonpresser.shape;

import java.awt.*;

public class ShapeFilledRectangle extends Shape {
    public ShapeFilledRectangle(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public double getArea() {
        double width = getWidth();
        double height = getHeight();
        return width * height;
    }
}