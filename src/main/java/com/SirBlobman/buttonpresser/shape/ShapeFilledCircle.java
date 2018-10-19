package com.SirBlobman.buttonpresser.shape;

import java.awt.*;

public class ShapeFilledCircle extends Shape {
    private final int radius;
    public ShapeFilledCircle(Color color, int x, int y, int radius) {
        super(color, x, y, 0, 0);
        this.radius = radius;
    }

    public int getRadius() {return radius;}
    public int getWidth() {return 2 * getRadius();}
    public int getHeight() {return 2 * getRadius();}

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(getColor());
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public double getArea() {
        double pi = Math.PI;
        double sqRadius = Math.pow(getRadius(), 2.0D);
        return pi * sqRadius;
    }
}