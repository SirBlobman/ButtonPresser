package com.SirBlobman.buttonpresser.shape;

import java.awt.*;

public abstract class Shape {
    private final int x, y, width, height;
    private Color color;

    public Shape(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if(super.equals(o)) return true;
        else if(o instanceof Shape) {
            Shape other = (Shape) o;
            return (other.getColor() == this.getColor() && other.getX() == this.getX() && other.getY() == this.getY() && other.getWidth() == this.getWidth() && other.getHeight() == this.getHeight());
        } else return false;
    }

    public abstract double getArea();

    public abstract void draw(Graphics graphics);

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Color getColor() {return this.color;}
}
